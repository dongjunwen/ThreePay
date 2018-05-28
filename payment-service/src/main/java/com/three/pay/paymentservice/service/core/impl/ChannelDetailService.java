package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentjdbc.entity.ChannelDetail;
import com.three.pay.paymentjdbc.respository.ChannelDetailRep;
import com.three.pay.paymentservice.service.core.IChannelDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:luiz
 * @Date: 2018/5/28 10:58
 * @Descripton:
 * @Modify :
 **/
@Service
public class ChannelDetailService implements IChannelDetail {
    @Autowired
    ChannelDetailRep channelDetailRep;
    @Override
    public ChannelDetail findByPayWay(String payWay) {
        return channelDetailRep.findByPayWay(payWay);
    }
}
