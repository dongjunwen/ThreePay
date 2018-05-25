package com.three.pay.paymentservice.service.core;

import com.three.pay.paymentapi.result.PayResult;
import com.three.pay.paymentcommon.po.MerOrderPo;

/**
 * @Author:luiz
 * @Date: 2018/5/24 17:27
 * @Descripton:
 * @Modify :
 **/
public interface IMerOrderService {
   PayResult<Integer> createOrder(MerOrderPo merOrderVo);
}
