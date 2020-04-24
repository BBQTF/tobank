package com.bbq.tobank.mapper;

import com.bbq.tobank.entity.Order;

/**
 * @author liutf
 * @date 2020-04-24
 */
public interface OrderMapper {
    /**
     * <!--乐观锁-限制订单处理-->
     *
     * @param id
     * @param setStatus
     * @param originStatus
     * @return
     */
    Integer updateStatusByPrimaryKeyAndStatus(String id, Integer setStatus, Integer originStatus);

    /**
     * 更新订单信息
     *
     * @param order
     */
    void updateByPrimaryKey(Order order);
}
