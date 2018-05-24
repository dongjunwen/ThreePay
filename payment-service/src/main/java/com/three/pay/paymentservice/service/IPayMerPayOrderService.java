package com.three.pay.paymentservice.service;

import com.three.pay.paymentapi.result.PayResult;
import com.three.pay.paymentcommon.vo.PayMerPayOrderVo;

/**
 * @Author:luiz
 * @Date: 2018/5/24 17:27
 * @Descripton:
 * @Modify :
 **/
public interface IPayMerPayOrderService {
   PayResult<Integer> createOrder(PayMerPayOrderVo payMerPayOrderVo);
}
