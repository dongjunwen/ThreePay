package com.three.pay.paymentservice.service.apiImpl.rest;

import com.three.pay.paymentapi.api.ITradeProcess;
import com.three.pay.paymentapi.enums.ServiceNameEnum;
import com.three.pay.paymentapi.result.PayResult;
import com.three.pay.paymentapi.vo.CommonReqParam;
import org.springframework.stereotype.Service;

/**
 * @Author:luiz
 * @Date: 2018/6/8 13:22
 * @Descripton:退款查询接口
 * @Modify :
 **/
@Service
public class MerRefundQueryImpl implements ITradeProcess {
    @Override
    public boolean isSupport(ServiceNameEnum serviceNameEnum) {
        return ServiceNameEnum.QUERY_REFUND.equals(serviceNameEnum);
    }

    @Override
    public PayResult execute(CommonReqParam commonReqVo) {
        return null;
    }
}
