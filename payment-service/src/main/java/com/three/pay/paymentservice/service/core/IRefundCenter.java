package com.three.pay.paymentservice.service.core;

import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentcommon.dto.MerChannelInfo;
import com.three.pay.paymentcommon.po.MerRefundOrderPo;
import com.three.pay.paymentjdbc.entity.PayRefundDetail; /**
 * @Author:luiz
 * @Date: 2018/6/8 14:48
 * @Descripton:退款中心接口
 * @Modify :
 **/
public interface IRefundCenter {
    void createRefund(MerChannelInfo merChannelInfo, CommonReqParam commonReqVo, MerRefundOrderPo merRefundOrderPo);

    void updateRefund(PayRefundDetail payRefundDetail);
}
