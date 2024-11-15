package com.stp.xxx.service;

import com.mybatisflex.core.service.IService;
import com.stp.xxx.dto.memo.MemoAddInputDTO;
import com.stp.xxx.dto.memo.MemoGetOutputDTO;
import com.stp.xxx.dto.memo.MemoQueryParam;
import com.stp.xxx.dto.memo.MemoQueryResult;
import com.stp.xxx.util.page.PageParam;
import com.stp.xxx.util.page.PageResult;
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

    List<MemoGetOutputDTO> getUserMemo(String username);

    PageResult<MemoQueryResult> queryMemo(PageParam<MemoQueryParam> param);
}
