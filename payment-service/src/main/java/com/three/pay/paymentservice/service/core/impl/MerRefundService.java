package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentcommon.po.MerPaySeqPo;
import com.three.pay.paymentjdbc.entity.MerRefundOrder;
import com.three.pay.paymentjdbc.respository.MerRefundOrderRep;
import com.three.pay.paymentservice.service.core.IMerRefund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:luiz
 * @Date: 2018/6/8 14:34
 * @Descripton:
 * @Modify :
 **/
@Service
public class MerRefundService implements IMerRefund {
    @Autowired
    MerRefundOrderRep merRefundOrderRep;
    @Override
    public List<MerRefundOrder> queryOrder(MerPaySeqPo merPaySeqPo) {
        return merRefundOrderRep.findByMerOrderNoAndMerPaySeq(merPaySeqPo.getMerOrderNo(),merPaySeqPo.getMerPaySeq());
    }
}
