package com.three.pay.paymentservice.service.core;

import com.three.pay.paymentchannel.param.ChannelRespParam;
import com.three.pay.paymentcommon.dto.MerOrderDto;

/**
 * @Author:luiz
 * @Date: 2018/5/30 19:54
 * @Descripton:
 * @Modify :
 **/
public interface IPayReqLog {

    void save(ChannelRespParam channelRespParam,MerOrderDto merOrderDto);
}
