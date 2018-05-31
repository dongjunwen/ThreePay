package com.three.pay.paymentservice.service.core;

import com.three.pay.paymentcommon.dto.MerChannelInfo;
import com.three.pay.paymentcommon.enums.ChannelActionEnum;
import com.three.pay.paymentjdbc.entity.PayOrderDetail;

/**
 * @Author:luiz
 * @Date: 2018/5/31 13:52
 * @Descripton:渠道路由中心
 * @Modify :
 **/
public interface IChannelRouteCenter {
    /**
     * 根据商户号、产品编码选择渠道对应的渠道
     * @param merNo
     * @param productNo
     */
    MerChannelInfo switchChannelRoute(String merNo, String productNo);

    MerChannelInfo buildMerChannelInfo(PayOrderDetail payOrderDetail, ChannelActionEnum channelActionEnum);
}
