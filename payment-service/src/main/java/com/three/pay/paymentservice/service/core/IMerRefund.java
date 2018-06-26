package com.three.pay.paymentservice.service.core;

import com.three.pay.paymentcommon.po.MerPaySeqPo;
import com.three.pay.paymentjdbc.entity.MerRefundOrder;

import java.util.List;

/**
 * @Author:luiz
 * @Date: 2018/6/8 14:29
 * @Descripton:商户退款操作接口
 * @Modify :
 **/
public interface IMerRefund {
    List<MerRefundOrder> queryOrder(MerPaySeqPo merPaySeqPo);

    MerRefundOrder findByRefundNo(String refundNo);
}
