package com.bbq.tobank.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author liutf
 * @date 2020-04-23
 */
@Data
public class OrderApply {
    private String id;
    private String orderId;
    private BigDecimal moneyAmount;
    private Long createTime;
    private Integer status;
}
