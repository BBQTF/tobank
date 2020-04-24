package com.bbq.tobank.service.impl;

import com.bbq.tobank.dto.ResponseDto;
import com.bbq.tobank.entity.OrderApply;
import com.bbq.tobank.service.BankService;
import org.springframework.stereotype.Service;

/**
 * @author liutf
 * @date 2020-04-24
 */
@Service
public class BankServiceImpl implements BankService {
    @Override
    public ResponseDto outMoney(OrderApply apply) {
        // 测试- 假数据
        return new ResponseDto("-1");
    }
}
