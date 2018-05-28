package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentjdbc.respository.MerOrderRepository;
import com.three.pay.paymentservice.service.core.IMerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author:luiz
 * @Date: 2018/5/24 17:48
 * @Descripton:商户收单
 * @Modify :
 **/
@Service
public class MerOrderService implements IMerOrder {
    @Autowired
    private MerOrderRepository payMerPayOrderRepository;

}
