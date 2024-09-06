package com.stp.xxx.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.stp.xxx.dto.memo.MemoAddInputDTO;
import com.stp.xxx.entity.Memo;
import com.stp.xxx.dao.MemoMapper;
import com.stp.xxx.entity.User;
import com.stp.xxx.service.MemoService;
import org.springframework.stereotype.Service;

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

    @Override
    public String add(MemoAddInputDTO inputDTO) {
        User user = (User) StpUtil.getSession().get("info");
        Memo memo = BeanUtil.copyProperties(inputDTO, Memo.class);
        memo.setId(IdUtil.fastSimpleUUID().toUpperCase());
        memo.setUserId(user.getId());
        if (save(memo)) {
            return memo.getId();
        }
        return "创建失败";
    }

    @Override
    public String getMemoJson(String username) {
        return "";
    }
}
