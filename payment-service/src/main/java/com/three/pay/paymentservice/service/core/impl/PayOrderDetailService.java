package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentjdbc.entity.PayOrderDetail;
import com.three.pay.paymentjdbc.respository.PayOrderDetailRep;
import com.three.pay.paymentservice.service.core.IPayOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:luiz
 * @Date: 2018/6/1 15:52
 * @Descripton:
 * @Modify :
 **/
@Service
public class PayOrderDetailService implements IPayOrderDetail {
    @Autowired
    PayOrderDetailRep payOrderDetailRep;
    @Override
    public PayOrderDetail findByUniqIndex(String paySeqNo) {
        return payOrderDetailRep.findByPaySeqNo(paySeqNo);
    }

    @Override
    public PayOrderDetail findByTradeNo(String tradeNo) {
        List<PayOrderDetail> payOrderDetails=payOrderDetailRep.findByTradeNo(tradeNo);
        if(payOrderDetails!=null && payOrderDetails.size()>=1) return payOrderDetails.get(0);
        return null;
    }


}
