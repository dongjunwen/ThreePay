package com.three.pay.paymentservice.service.core;

import com.three.pay.paymentjdbc.entity.PayNotifyLog;
/**
 * @Author:luiz
 * @Date: 2018/5/30 19:42
 * @Descripton:
 * @Modify :
 **/
public interface IPayNotifyLog {
    void saveNotifyLog(PayNotifyLog payNotifyLog);
}
