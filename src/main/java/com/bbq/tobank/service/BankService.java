package com.bbq.tobank.service;

import com.bbq.tobank.dto.ResponseDto;
import com.bbq.tobank.entity.OrderApply;

/**
 * @author liutf
 * @date 2020-04-24
 */
public interface BankService {
    /**
     * 扣款服务
     *
     * @param apply
     * @return
     */
    ResponseDto outMoney(OrderApply apply);
}
