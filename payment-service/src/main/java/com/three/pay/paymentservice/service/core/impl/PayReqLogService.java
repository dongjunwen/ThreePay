package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentchannel.param.ChannelRespParam;
import com.three.pay.paymentcommon.dto.MerOrderDto;
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
    public void save(ChannelRespParam channelRespParam,MerOrderDto merOrderDto) {
        PayReqLog payReqLog=new PayReqLog();
        payReqLog.setTradeNo(merOrderDto.getInnerSeqNo());
        payReqLog.setTradeType(merOrderDto.getTradeType());
        payReqLog.setPayWay(merOrderDto.getPayWay());
        payReqLog.setPayWayName(merOrderDto.getPayWayName());
        payReqLog.setSynContent(channelRespParam.getRespContent());

        payReqLogRep.save(payReqLog);
    }
}
