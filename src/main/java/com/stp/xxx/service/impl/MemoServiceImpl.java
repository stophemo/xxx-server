package com.stp.xxx.service.impl;

import org.dromara.hutool.core.bean.BeanUtil;
import org.dromara.hutool.core.cache.CacheUtil;
import org.dromara.hutool.core.cache.impl.TimedCache;
import org.dromara.hutool.core.collection.CollUtil;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.stp.xxx.dto.memo.MemoGetOutputDTO;
import com.stp.xxx.dto.memo.MemoQueryParam;
import com.stp.xxx.dto.memo.MemoQueryResult;
import com.stp.xxx.entity.table.MemoTableDef;
import com.stp.xxx.entity.table.UserTableDef;
import com.stp.xxx.util.page.PageParam;
import com.stp.xxx.util.page.PageResult;
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
import org.dromara.hutool.core.data.id.IdUtil;
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
                .eq("tag", memo.getTag())
                .eq("title", memo.getTitle()))) {
            throw new BusinessException(ErrorCodeEnum.MEMO_ALREADY_EXISTS);
        } else if (save(memo)) {
            return memo.getId();
        } else {
            return "创建失败";
        }
    }

    @Override
    public List<MemoGetOutputDTO> getUserMemo(String username) {
        // 校验用户名
        long userNameCount = userService.count(QueryWrapper.create()
                .eq("name", username));

        if (userNameCount != 1) {
            throw new BusinessException(ErrorCodeEnum.USER_NOT_FOUND.getCode(), "用户名不存在或用户名异常");
        }

        List<Memo> memos = mapper.selectListByQuery(QueryWrapper.create()
                .select()
                .from(Memo.class)
                .leftJoin(User.class).on(Memo::getUserId, User::getId)
                .eq(User::getName, username));

        if (CollUtil.isNotEmpty(memos)) {
            return memos.stream().collect(Collectors.groupingBy(Memo::getTag)).entrySet().stream().map(
                    entry -> {
                        if (CollUtil.isEmpty(entry.getValue())) {
                            return null;
                        }
                        return new MemoGetOutputDTO(entry.getKey(), entry.getValue().stream()
                                .map(memo -> new MemoGetOutputDTO.Content(memo.getId(), memo.getTitle(), memo.getContent()))
                                .toList());
                    }
            ).collect(Collectors.toList());
        }
        return null;
    }

    private static final TimedCache<MemoQueryParam, Long> PAGECOUNT_CACHE = CacheUtil.newTimedCache(60 * 1000L);

    @Override
    public PageResult<MemoQueryResult> queryMemo(PageParam<MemoQueryParam> param) {
        MemoQueryParam filter = param.getFilter();
        long totalRow = PAGECOUNT_CACHE.get(filter) == null ? -1 : PAGECOUNT_CACHE.get(filter);

        Page<MemoQueryResult> res = QueryChain.of(mapper)
                .select(MemoTableDef.MEMO.ALL_COLUMNS,
                        UserTableDef.USER.NAME.as("username"))
                .from(Memo.class)
                .leftJoin(User.class)
                .on(Memo::getUserId, User::getId)
                .where(User::getId).eq(filter.getUserId())
                .and(User::getName).eq(filter.getUsername())
                .and(Memo::getId).eq(filter.getId())
                .and(Memo::getTitle).like(filter.getTitle())
                .and(Memo::getContent).like(filter.getContent())
                .and(Memo::getTag).like(filter.getTag())
                .and(Memo::getPriority).eq(filter.getPriority())
                .and(Memo::getCreateTime).between(filter.getCreateStartDate(), filter.getCreateEndDate())
                .and(Memo::getUpdateTime).between(filter.getUpdateStartDate(), filter.getUpdateEndDate())
                .orderBy(Memo::getUpdateTime, false)
                .pageAs(Page.of(param.getPageNumber(), param.getPageSize(), totalRow), MemoQueryResult.class);
        if (totalRow == -1) {
            PAGECOUNT_CACHE.put(filter, res.getTotalRow());
        }
        return new PageResult<>(res.getTotalPage(), res.getTotalRow(), res.getRecords());
    }

}
