package com.three.pay.paymentchannel;

import com.three.pay.paymentchannel.param.ChannelReqParam;
import com.three.pay.paymentchannel.param.ChannelRespParam;
import com.three.pay.paymentcommon.dto.MerChannelInfo;
import com.three.pay.paymentcommon.enums.ChannelActionEnum;
import com.three.pay.paymentcommon.enums.PayWayEnum;

/**
 * @Author:luiz
 * @Date: 2018/5/28 11:22
 * @Descripton:渠道核心处理接口
 * @Modify :
 **/
public interface IChannelCore {
    boolean isSupport(PayWayEnum payWayEnum, ChannelActionEnum channelActionEnum);

    ChannelRespParam channelProcess(MerChannelInfo merChannelInfo, ChannelReqParam channelReqParam);

}
