package com.bbq.tobank.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author liutf
 * @date 2020-04-20
 */
@Data
public class Order {
    private String id;
    private BigDecimal moneyAmount;
    private Long createTime;
    private Integer status;
}
