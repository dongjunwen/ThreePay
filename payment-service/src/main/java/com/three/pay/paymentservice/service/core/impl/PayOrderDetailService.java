package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentjdbc.entity.PayOrderDetail;
import com.three.pay.paymentjdbc.respository.PayOrderDetailRep;
import com.three.pay.paymentservice.service.core.IPayOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
