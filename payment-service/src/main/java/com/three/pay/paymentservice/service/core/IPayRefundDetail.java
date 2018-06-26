package com.three.pay.paymentservice.service.core;

import com.three.pay.paymentjdbc.entity.PayRefundDetail;

/**
 * @Author:luiz
 * @Date: 2018/6/26 19:18
 * @Descripton:
 * @Modify :
 **/
public interface IPayRefundDetail {
    PayRefundDetail findByTradeNo(String tradeNo);
}
