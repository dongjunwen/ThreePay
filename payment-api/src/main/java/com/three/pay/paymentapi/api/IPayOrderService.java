package com.three.pay.paymentapi.api;

import com.three.pay.paymentapi.result.PayResult;
import com.three.pay.paymentapi.vo.admin.PayOrderCondParam;

/**
 * @Author:luiz
 * @Date: 2018/6/4 10:55
 * @Descripton:订单查询接口
 * @Modify :
 **/
public interface IPayOrderService {
    /**
     * 按照条件查询订单信息
     * @param payOrderCondParam
     * @return
     */
    PayResult findOrderPage(PayOrderCondParam payOrderCondParam);
}
