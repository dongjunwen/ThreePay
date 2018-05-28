package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentjdbc.entity.ChannelInfo;
import com.three.pay.paymentjdbc.respository.ChannelInfoRep;
import com.three.pay.paymentservice.service.core.IChannelInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:luiz
 * @Date: 2018/5/28 15:35
 * @Descripton:
 * @Modify :
 **/
public class ChannelService implements IChannelInfo {
    @Autowired
    ChannelInfoRep channelInfoRep;
    @Override
    public ChannelInfo findByChannelCode(String channelCode) {
        return channelInfoRep.findByChannelCode(channelCode);
    }
}
