package com.three.pay.paymentservice.service.impl;

import com.three.pay.paymentapi.result.PayResult;
import com.three.pay.paymentcommon.vo.PayMerPayOrderVo;
import com.three.pay.paymentjdbc.entity.PayMerPayOrder;
import com.three.pay.paymentjdbc.respository.PayMerPayOrderRepository;
import com.three.pay.paymentservice.service.IPayMerPayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author:luiz
 * @Date: 2018/5/24 17:48
 * @Descripton:商户收单
 * @Modify :
 **/
@Service
public class PayMerPayOrderService implements IPayMerPayOrderService {
    @Autowired
    private PayMerPayOrderRepository payMerPayOrderRepository;
    @Override
    public PayResult<Integer> createOrder(PayMerPayOrderVo payMerPayOrderVo) {
        PayMerPayOrder payMerPayOrder=new PayMerPayOrder();
        payMerPayOrder.setMerNo("00000000001");
        payMerPayOrderRepository.saveAndFlush(payMerPayOrder);
        return PayResult.newSuccess(1);
    }
}
