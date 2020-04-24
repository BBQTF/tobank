package com.bbq.tobank.controller;

import com.bbq.tobank.common.ApiResponse;
import com.bbq.tobank.entity.Order;
import com.bbq.tobank.service.impl.OrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author liutf
 * @date 2020-04-24
 */
@RestController
@Api("订单")
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderServiceImpl orderService;

    @ApiOperation("支付")
    @PostMapping("/pay")
    public void pay(Order order) {
        order.setId("1");
        order.setMoneyAmount(new BigDecimal(1000));
        Long now = System.currentTimeMillis();
        order.setCreateTime(now);
        order.setStatus(0);
        orderService.pay(order);
    }
}
