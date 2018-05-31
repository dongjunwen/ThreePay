package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentjdbc.entity.PayNotifyLog;
import com.three.pay.paymentjdbc.respository.PayNotifyLogRep;
import com.three.pay.paymentservice.service.core.IPayNotifyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:luiz
 * @Date: 2018/5/30 19:52
 * @Descripton:
 * @Modify :
 **/
@Service
public class PayNotifyLogService implements IPayNotifyLog {
    @Autowired
    PayNotifyLogRep payNotifyLogRep;
    @Override
    public void saveNotifyLog(PayNotifyLog payNotifyLog) {
        payNotifyLogRep.saveAndFlush(payNotifyLog);
    }
}
