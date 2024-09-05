package com.stp.xxx.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.stp.xxx.dao.UserMapper;
import com.stp.xxx.dto.memo.MemoDTO;
import com.stp.xxx.entity.Memo;
import com.stp.xxx.dao.MemoMapper;
import com.stp.xxx.service.MemoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
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

    @Resource
    private UserMapper userMapper;

    @Override
    public String add(MemoDTO memoDTO) {
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        Memo memo = BeanUtil.copyProperties(memoDTO, Memo.class);
        memo.setId(IdUtil.fastSimpleUUID().toUpperCase());
        if (baseMapper.insert(memo) == 1) {
            return memo.getId();
        }
        return "创建失败";
    }
}
