package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentchannel.param.ChannelRespParam;
import com.three.pay.paymentcommon.dto.MerChannelInfo;
import com.three.pay.paymentjdbc.entity.PayReqLog;
import com.three.pay.paymentjdbc.respository.PayReqLogRep;
import com.three.pay.paymentservice.service.core.IPayReqLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:luiz
 * @Date: 2018/5/30 19:54
 * @Descripton:
 * @Modify :
 **/
@Service
public class PayReqLogService implements IPayReqLog {
    @Autowired
    PayReqLogRep payReqLogRep;
    @Override
    public void save(ChannelRespParam channelRespParam,MerChannelInfo merChannelInfo) {
        PayReqLog payReqLog=new PayReqLog();
        payReqLog.setTradeNo(merChannelInfo.getInnerSeqNo());
        payReqLog.setTradeType(merChannelInfo.getTradeType());
        payReqLog.setPayWay(merChannelInfo.getPayWay());
        payReqLog.setPayWayName(merChannelInfo.getPayWayName());
        payReqLog.setSynContent(channelRespParam.getRespContent());

        payReqLogRep.save(payReqLog);
    }
}
