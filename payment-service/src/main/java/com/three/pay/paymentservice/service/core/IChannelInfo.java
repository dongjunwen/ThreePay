package com.three.pay.paymentservice.service.core;

import com.three.pay.paymentjdbc.entity.ChannelInfo;

/**
 * @Author:luiz
 * @Date: 2018/5/28 10:02
 * @Descripton:
 * @Modify :
 **/
public interface IChannelInfo {
    ChannelInfo findByChannelCode(String channelCode);
}
