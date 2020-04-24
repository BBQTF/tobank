package com.bbq.tobank.common;

import lombok.Getter;

/**
 * @author liutf
 * @date 2020-04-24
 */
public enum OrderStatusEnum {

    /**
     * 处理失败
     */
    FAITH(-1),

    /**
     * 默认状态-未处理
     */
    DEFAULT(0),

    /**
     * 处理中
     */
    DEALING(1),

    /**
     * 成功
     */
    SUCCESS(0000);

    @Getter
    private int statusCode;

    OrderStatusEnum(int statusCode) {
        this.statusCode = statusCode;
    }
}
