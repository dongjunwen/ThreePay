package com.three.pay.paymentapi.api;

import com.three.pay.paymentapi.enums.ServiceNameEnum;
import com.three.pay.paymentapi.result.PayResult;
import com.three.pay.paymentapi.vo.CommonReqParam;

/**
 * @Author:luiz
 * @Date: 2018/5/25 16:30
 * @Descripton:
 * @Modify :
 **/
public interface ITradeProcess {

    boolean isSupport(ServiceNameEnum serviceNameEnum);
    /**
     * 交易统一入口
     * @param commonReqVo
     * @return
     */
    PayResult execute(CommonReqParam commonReqVo);
}
