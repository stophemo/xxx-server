package com.stp.xxx.service;

import com.stp.xxx.dto.memo.MemoAddInputDTO;
import com.stp.xxx.entity.Memo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 备忘录表 服务类
 * </p>
 *
 * @author jackman
 * @since 2024-08-26
 */
public interface MemoService extends IService<Memo> {

    String add(MemoAddInputDTO inputDTO);
}
