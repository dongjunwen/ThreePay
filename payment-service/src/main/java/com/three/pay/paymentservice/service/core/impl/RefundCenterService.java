package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentcommon.dto.MerChannelInfo;
import com.three.pay.paymentcommon.enums.RefundStatusEnum;
import com.three.pay.paymentcommon.enums.TradeStatusEnum;
import com.three.pay.paymentcommon.enums.TradeTypeEnum;
import com.three.pay.paymentcommon.po.MerRefundOrderPo;
import com.three.pay.paymentcommon.utils.IDUtils;
import com.three.pay.paymentjdbc.entity.MerRefundOrder;
import com.three.pay.paymentjdbc.entity.PayRefundDetail;
import com.three.pay.paymentjdbc.entity.PayTradeTotal;
import com.three.pay.paymentjdbc.respository.MerRefundOrderRep;
import com.three.pay.paymentjdbc.respository.PayRefundDetailRep;
import com.three.pay.paymentjdbc.respository.PayTradeTotalRep;
import com.three.pay.paymentservice.service.core.IRefundCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Author:luiz
 * @Date: 2018/6/8 15:01
 * @Descripton:
 * @Modify :
 **/
@Service
public class RefundCenterService implements IRefundCenter {
    private static final Logger logger= LoggerFactory.getLogger(RefundCenterService.class);
    @Autowired
    MerRefundOrderRep merRefundOrderRep;
    @Autowired
    PayTradeTotalRep payTradeTotalRep;
    @Autowired
    PayRefundDetailRep payRefundDetailRep;

    @Override
    public void createRefund(MerChannelInfo merChannelInfo, CommonReqParam commonReqVo, MerRefundOrderPo merRefundOrderPo) {
        String tradeNo=IDUtils.genIdStr("D");
        java.util.Date nowDate=new java.util.Date();

        MerRefundOrder merRefundOrder=new MerRefundOrder();
        merRefundOrder.setMerNo(merChannelInfo.getMerNo());
        merRefundOrder.setMerName(merChannelInfo.getMerName());
        String refundNo= IDUtils.genIdStr("R");
        merRefundOrder.setRefundNo(refundNo);
        merRefundOrder.setTradeNo(tradeNo);
        merRefundOrder.setMerOrderNo(merRefundOrderPo.getMerOrderNo());
        merRefundOrder.setMerPaySeq(merRefundOrderPo.getMerPaySeq());
        merRefundOrder.setRefundAmt(new BigDecimal(merRefundOrderPo.getRefundAmt()));
        merRefundOrder.setRefundDesc(merRefundOrderPo.getRefundDesc());
        merRefundOrder.setRefundStatus(RefundStatusEnum.REFUND_INIT.getCode());
        merRefundOrder.setRefundWay(merChannelInfo.getPayWay());
        merRefundOrder.setUserNo(merChannelInfo.getUserNo());
        merRefundOrder.setPayNo(merChannelInfo.getPayNo());
        merRefundOrder.setRefundTime(new Timestamp(nowDate.getTime()));
        merRefundOrder.setCreateTime(new Timestamp(nowDate.getTime()));
        merRefundOrder.setModiTime(new Timestamp(nowDate.getTime()));
        merRefundOrderRep.saveAndFlush(merRefundOrder);

        PayTradeTotal payTradeTotal=new PayTradeTotal();
        payTradeTotal.setTradeNo(tradeNo);
        payTradeTotal.setTradeAmt(new BigDecimal(merRefundOrderPo.getRefundAmt()));
        payTradeTotal.setMerNo(merChannelInfo.getMerNo());
        payTradeTotal.setMerName(merChannelInfo.getMerName());
        payTradeTotal.setTradeTime(new java.sql.Timestamp(nowDate.getTime()));
        payTradeTotal.setUserNo(merChannelInfo.getUserNo());
        payTradeTotal.setTradeType(TradeTypeEnum.REFUND.getCode());
        payTradeTotal.setTradeStatus(TradeStatusEnum.INIT.getCode());
        payTradeTotal.setCreateTime(new java.sql.Timestamp(nowDate.getTime()));
        payTradeTotal.setModiTime(new java.sql.Timestamp(nowDate.getTime()));
        payTradeTotalRep.saveAndFlush(payTradeTotal);
        PayRefundDetail payRefundDetail=new PayRefundDetail();
        String refundSeqNo=IDUtils.genIdStr("L");
        payRefundDetail.setTradeNo(tradeNo);
        payRefundDetail.setRefundSeqNo(refundSeqNo);
        payRefundDetail.setPaySeqNo(merChannelInfo.getInnerSeqNo());
        payRefundDetail.setUserNo(merChannelInfo.getUserNo());
        payRefundDetail.setRefundWay(merChannelInfo.getPayWay());
        payRefundDetail.setRefundAmt(new BigDecimal(merRefundOrderPo.getRefundAmt()));
        payRefundDetail.setRefundDesc(merRefundOrderPo.getRefundDesc());
        payRefundDetail.setRefundStatus(RefundStatusEnum.REFUND_INIT.getCode());
        payRefundDetail.setChannelCode(merChannelInfo.getChannelCode());
        payRefundDetail.setChannelName(merChannelInfo.getChannelName());
        payRefundDetail.setMerNo(merChannelInfo.getMerNo());
        payRefundDetail.setMerName(merChannelInfo.getMerName());
        payRefundDetail.setChannelPartenerNo(merChannelInfo.getChannelPartenerNo());
        payRefundDetail.setChannelPartenerName(merChannelInfo.getChannelPartenerName());
        payRefundDetail.setCreateTime(new Timestamp(nowDate.getTime()));
        payRefundDetail.setModiTime(new Timestamp(nowDate.getTime()));
        payRefundDetailRep.saveAndFlush(payRefundDetail);
        merChannelInfo.setInnerRefundSeqNo(refundSeqNo);
    }

    @Transactional
    @Override
    public void updateRefund(PayRefundDetail payRefundDetail) {
        PayRefundDetail oldPayRefundDetail=payRefundDetailRep.findByRefundSeqNo(payRefundDetail.getRefundSeqNo());
        if(oldPayRefundDetail==null || RefundStatusEnum.REFUND_INIT.getCode()!=oldPayRefundDetail.getRefundStatus()){
            logger.info("[修改订单]状态不是处理中,参数:{}",payRefundDetail);
            return;
        }
        java.util.Date nowDate=new java.util.Date();
        oldPayRefundDetail.setRefundStatus(payRefundDetail.getRefundStatus());
        oldPayRefundDetail.setRefundSuccessTime(payRefundDetail.getRefundSuccessTime());
        oldPayRefundDetail.setPaylerAcct(payRefundDetail.getPaylerAcct());
        oldPayRefundDetail.setRefundCurrency(payRefundDetail.getRefundCurrency());
        oldPayRefundDetail.setThirdRefundDiscountAmt(payRefundDetail.getThirdRefundDiscountAmt());
        oldPayRefundDetail.setBuyRefundAmt(payRefundDetail.getBuyRefundAmt());
        oldPayRefundDetail.setMerRefundDiscountAmt(payRefundDetail.getMerRefundDiscountAmt());
        oldPayRefundDetail.setModiTime(new Timestamp(nowDate.getTime()));
        payRefundDetailRep.saveAndFlush(oldPayRefundDetail);
        PayTradeTotal payTradeTotal=new PayTradeTotal();
        payTradeTotal.setTradeNo(oldPayRefundDetail.getTradeNo());
        TradeStatusEnum tradeStatusEnum=TradeStatusEnum.parse(String.valueOf(payRefundDetail.getRefundStatus()));
        payTradeTotal.setTradeStatus(tradeStatusEnum.getCode());
        payTradeTotal.setModiTime(new java.sql.Timestamp(nowDate.getTime()) );
        payTradeTotalRep.updateByTradeNo(payTradeTotal.getTradeStatus(),payTradeTotal.getModiTime(),payTradeTotal.getTradeNo());
        MerRefundOrder merRefundOrder=new MerRefundOrder();
        merRefundOrder.setTradeNo(oldPayRefundDetail.getTradeNo());
        merRefundOrder.setRefundStatus(oldPayRefundDetail.getRefundStatus());
        merRefundOrder.setModiTime(new Timestamp(nowDate.getTime()));
        merRefundOrderRep.updateByTradeNo(merRefundOrder.getRefundStatus(),merRefundOrder.getModiTime(),merRefundOrder.getTradeNo());

    }
}
