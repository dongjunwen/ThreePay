package com.three.pay.paymentservice.service.core;

import com.three.pay.paymentjdbc.entity.PayOrderDetail;

/**
 * @Author:luiz
 * @Date: 2018/6/1 15:52
 * @Descripton:
 * @Modify :
 **/
public interface IPayOrderDetail {
    PayOrderDetail findByUniqIndex(String paySeqNo);
    PayOrderDetail findByTradeNo(String tradeNo);
}
