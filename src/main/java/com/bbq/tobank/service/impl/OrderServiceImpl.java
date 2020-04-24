package com.bbq.tobank.service.impl;

import com.bbq.tobank.common.OrderStatusEnum;
import com.bbq.tobank.dto.ResponseDto;
import com.bbq.tobank.entity.Order;
import com.bbq.tobank.entity.OrderApply;
import com.bbq.tobank.mapper.OrderApplyMapper;
import com.bbq.tobank.mapper.OrderMapper;
import com.bbq.tobank.service.BankService;
import com.bbq.tobank.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author liutf
 * @date 2020-04-20
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderApplyMapper orderApplyMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private BankService bankService;
    @Autowired
    private TransactionTemplate transactionTemplate;
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    /**
     * 订单支付
     * @param order
     */
    @Transactional(propagation = Propagation.NEVER)// 关闭事务的传播性
    public Integer pay(Order order) {
        try {
            /*银行服务不是幂等时需创建订单申请表，使用申请表中的流水号来完成支付；若使用订单id支付，支付失败时，银行将不再处理同一订单
            号业务，会一直返回订单已处理*/
            // spring编程式事务
            Integer applyId = transactionTemplate.execute(new TransactionCallback<Integer>() {
                @Override
                public Integer doInTransaction(TransactionStatus transactionStatus) {
                    // 乐观锁-订单状态
                    boolean lockStatus = 1 == orderMapper.updateStatusByPrimaryKeyAndStatus(order.getId(),
                            OrderStatusEnum.DEALING.getStatusCode(), OrderStatusEnum.DEFAULT.getStatusCode());
                    // 获取锁成功
                    if (lockStatus) {
                        // 创建订单支付申请
                        OrderApply apply = new OrderApply();
                        apply.setId(UUID.randomUUID().toString());
                        // 绑定订单关系
                        apply.setOrderId(order.getId());
                        // 设置订单申请记录的状态
                        apply.setStatus(OrderStatusEnum.DEFAULT.getStatusCode());
                        // 插入申请记录&&返回流水号
                        Integer applyId = orderApplyMapper.insert(apply);
                        return applyId;
                    }
                    return -1;
                }
            });
            // 获取到锁
            if(applyId > 0){
                // 远程调用银行接口
                OrderApply apply = new OrderApply();
                apply.setOrderId(order.getId());
                apply.setMoneyAmount(order.getMoneyAmount());
                ResponseDto responseDto = bankService.outMoney(apply);

                if(responseDto != null){
                    if("0000".equals(responseDto.getCode())){
                        order.setStatus(OrderStatusEnum.SUCCESS.getStatusCode());
                    }else {
                        order.setStatus(OrderStatusEnum.FAITH.getStatusCode());
                    }
                }
                transactionTemplate.execute(new TransactionCallback<Object>() {
                    @Override
                    public Object doInTransaction(TransactionStatus transactionStatus) {
                        // 更新订单申请记录表
                        apply.setStatus(order.getStatus());
                        orderApplyMapper.updateByPrimaryKey(apply);
                        // 更新订单记录表
                        orderMapper.updateByPrimaryKey(order);
                        return null;
                    }
                });
            }else {
                log.info("获取锁失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return order.getStatus();
    }
}
