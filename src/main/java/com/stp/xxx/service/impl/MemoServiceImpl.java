package com.stp.xxx.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.stp.xxx.dto.memo.MemoGetOutputDTO;
import com.stp.xxx.util.TokenUtil;
import com.stp.xxx.config.exception.BusinessException;
import com.stp.xxx.config.exception.ErrorCodeEnum;
import com.stp.xxx.dto.memo.MemoAddInputDTO;
import com.stp.xxx.entity.Memo;
import com.stp.xxx.dao.MemoMapper;
import com.stp.xxx.entity.User;
import com.stp.xxx.service.MemoService;
import com.stp.xxx.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 备忘录表 服务实现类
 * </p>
 *
 * @author jackman
 * @since 2024-08-26
 */
@Service
public class MemoServiceImpl extends ServiceImpl<MemoMapper, Memo> implements MemoService {

    @Resource
    private UserService userService;
    @Resource
    private MemoMapper memoMapper;

    @Override
    public String add(MemoAddInputDTO inputDTO) {
        User user = TokenUtil.getTokenInfo();
        Memo memo = BeanUtil.copyProperties(inputDTO, Memo.class);
        memo.setId(IdUtil.fastSimpleUUID().toUpperCase());
        memo.setUserId(user.getId());
        // 校验是否已存在
        if (exists(QueryWrapper.create()
                .eq("user_id", memo.getUserId())
                .eq("tags", memo.getTags())
                .eq("title", memo.getTitle()))) {
            throw new BusinessException(ErrorCodeEnum.MEMO_ALREADY_EXISTS);
        } else if (save(memo)) {
            return memo.getId();
        } else {
            return "创建失败";
        }
    }

    @Override
    public List<MemoGetOutputDTO> getMemo(String username) {
        // 校验用户名
        long userNameCount = userService.count(QueryWrapper.create()
                .eq("name", username));

        if (userNameCount != 1) {
            throw new BusinessException(ErrorCodeEnum.USER_NOT_FOUND.getCode(), "用户名不存在或用户名异常");
        }

        List<Memo> memos = memoMapper.selectListByQuery(QueryWrapper.create()
                .select()
                .from(Memo.class)
                .leftJoin(User.class).on(Memo::getUserId, User::getId)
                .eq(User::getName, username));

        if (CollUtil.isNotEmpty(memos)) {
            return memos.stream().collect(Collectors.groupingBy(Memo::getTags)).entrySet().stream().map(
                    entry -> {
                        if (CollUtil.isEmpty(entry.getValue())) {
                            return null;
                        }
                        return new MemoGetOutputDTO(entry.getKey(), entry.getValue().stream()
                                .map(memo -> new MemoGetOutputDTO.Content(memo.getTitle(), memo.getContent()))
                                .toList());
                    }
            ).collect(Collectors.toList());
        }
        return null;
    }
}
