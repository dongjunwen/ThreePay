package com.three.pay.paymentservice.service.channel;

import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentchannel.param.ChannelRespParam;
import com.three.pay.paymentcommon.dto.MerChannelInfo;
/**
 * @Author:luiz
 * @Date: 2018/5/28 11:03
 * @Descripton:
 * @Modify :
 **/
public interface IChannelService {
    ChannelRespParam channelProcess(MerChannelInfo merChannelInfo, CommonReqParam commonReqVo);
}
