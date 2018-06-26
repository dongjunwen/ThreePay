package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentjdbc.entity.PayRefundDetail;
import com.three.pay.paymentjdbc.respository.PayRefundDetailRep;
import com.three.pay.paymentservice.service.core.IPayRefundDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:luiz
 * @Date: 2018/6/26 19:20
 * @Descripton:
 * @Modify :
 **/
@Service
public class PayRefundDetailService implements IPayRefundDetail {
    @Autowired
    PayRefundDetailRep payRefundDetailRep;
    @Override
    public PayRefundDetail findByTradeNo(String tradeNo) {
        return  payRefundDetailRep.findByTradeNo(tradeNo);
    }
}
