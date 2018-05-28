package com.three.pay.paymentservice.service.core;

import com.three.pay.paymentjdbc.entity.MerInfo;

/**
 * @Author:luiz
 * @Date: 2018/5/28 10:00
 * @Descripton:商户信息接口
 * @Modify :
 **/
public interface IMerInfo {
    MerInfo findByMerNo(String merNo);
}
