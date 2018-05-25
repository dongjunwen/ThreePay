package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentapi.result.PayResult;
import com.three.pay.paymentcommon.po.MerOrderPo;
import com.three.pay.paymentjdbc.entity.MerOrder;
import com.three.pay.paymentjdbc.respository.MerOrderRepository;
import com.three.pay.paymentservice.service.core.IMerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author:luiz
 * @Date: 2018/5/24 17:48
 * @Descripton:商户收单
 * @Modify :
 **/
@Service
public class MerPayOrderService implements IMerOrderService {
    @Autowired
    private MerOrderRepository payMerPayOrderRepository;
    @Override
    public PayResult<Integer> createOrder(MerOrderPo merOrderVo) {
        MerOrder payMerPayOrder=new MerOrder();
        payMerPayOrder.setMerNo("00000000001");
        payMerPayOrderRepository.saveAndFlush(payMerPayOrder);
        return PayResult.newSuccess(1);
    }
}
