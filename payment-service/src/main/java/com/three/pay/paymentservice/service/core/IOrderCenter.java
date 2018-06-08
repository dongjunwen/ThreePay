package com.three.pay.paymentservice.service.core;

import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentcommon.dto.MerChannelInfo;
import com.three.pay.paymentcommon.po.MerUnionOrderPo;
import com.three.pay.paymentcommon.po.MerOrderQueryPo;
import com.three.pay.paymentcommon.po.notify.NotifyPayParamPo;
import com.three.pay.paymentjdbc.entity.PayOrderDetail;

/**
 * @Author:luiz
 * @Date: 2018/5/28 15:18
 * @Descripton:
 * @Modify :
 **/
public interface IOrderCenter {
    void createOrder(MerChannelInfo merChannelInfo, CommonReqParam commonReqVo, MerUnionOrderPo merUnionOrderPo);

    void notifyOrder(NotifyPayParamPo notifyPayParamPo);

    void updateOrder( PayOrderDetail payOrderDetail);

    PayOrderDetail queryOrder(MerOrderQueryPo merOrderQueryPo);
}
