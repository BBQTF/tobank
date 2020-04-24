package com.bbq.tobank.mapper;

import com.bbq.tobank.entity.OrderApply;

/**
 * @author liutf
 * @date 2020-04-24
 */
public interface OrderApplyMapper {
    /**
     * 插入申请记录
     *
     * @param apply
     * @return
     */
    Integer insert(OrderApply apply);

    /**
     * 更新申请记录
     *
     * @param apply
     */
    void updateByPrimaryKey(OrderApply apply);
}
