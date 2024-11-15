package com.stp.xxx.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import com.stp.xxx.dto.memo.MemoAddInputDTO;
import com.stp.xxx.dto.memo.MemoGetOutputDTO;
import com.stp.xxx.entity.Memo;

import java.util.List;

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

    List<MemoGetOutputDTO> getMemo(String username);

    Page<Memo> pageQuery(Page);
}
