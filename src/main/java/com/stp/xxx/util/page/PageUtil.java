package com.stp.xxx.util.page;

import cn.hutool.cache.CacheUtil;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;

public class PageUtil {

    public static  <T, R> PageResult<R> pageSelect(BaseMapper<T> mapper, PageParam<T> param, Class<R> asType) {
        QueryWrapper queryWrapper = QueryWrapper.create(param.getFilter());
        Page<R> res = mapper.paginateAs(param.getPageNumber(), param.getPageSize(), queryWrapper, asType);
        return new PageResult<R>(res.getTotalRow(), res.getTotalPage(), res.getRecords());
    }
}
