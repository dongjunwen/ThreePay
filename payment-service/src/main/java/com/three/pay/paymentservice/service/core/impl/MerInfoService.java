package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentjdbc.entity.MerInfo;
import com.three.pay.paymentjdbc.respository.MerInfoRep;
import com.three.pay.paymentservice.service.core.IMerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:luiz
 * @Date: 2018/5/28 10:33
 * @Descripton:
 * @Modify :
 **/
@Service
public class MerInfoService implements IMerInfo {
    @Autowired
    MerInfoRep merInfoRep;
    @Override
    public MerInfo findByMerNo(String merNo) {
        return merInfoRep.findByMerNo(merNo);
    }
}
