package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentcommon.dto.MerChannelInfo;
import com.three.pay.paymentcommon.enums.PayStatusEnum;
import com.three.pay.paymentcommon.enums.TradeStatusEnum;
import com.three.pay.paymentcommon.enums.TradeTypeEnum;
import com.three.pay.paymentcommon.po.MerOrderPo;
import com.three.pay.paymentcommon.po.MerOrderQueryPo;
import com.three.pay.paymentcommon.po.MerPaySeqPo;
import com.three.pay.paymentcommon.po.notify.NotifyPayParamPo;
import com.three.pay.paymentcommon.utils.DateUtil;
import com.three.pay.paymentcommon.utils.IDUtils;
import com.three.pay.paymentjdbc.entity.MerOrder;
import com.three.pay.paymentjdbc.entity.PayOrderDetail;
import com.three.pay.paymentjdbc.entity.PayTradeTotal;
import com.three.pay.paymentjdbc.respository.MerOrderRep;
import com.three.pay.paymentjdbc.respository.PayOrderDetailRep;
import com.three.pay.paymentjdbc.respository.PayTradeTotalRep;
import com.three.pay.paymentservice.service.core.IMerOrderDetail;
import com.three.pay.paymentservice.service.core.IOrderCenter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:luiz
 * @Date: 2018/5/28 15:20
 * @Descripton:
 * @Modify :
 **/
@Service
public class OrderCenterService implements IOrderCenter {
    private static final Logger logger=LoggerFactory.getLogger(OrderCenterService.class);
    @Autowired
    MerOrderRep merOrderRep;
    @Autowired
    IMerOrderDetail iMerOrderDetail;
    @Autowired
    PayTradeTotalRep payTradeTotalRep;
    @Autowired
    PayOrderDetailRep payOrderDetailRep;

    @Override
    @Transactional
    public void createOrder(MerChannelInfo merChannelInfo, CommonReqParam commonReqVo, MerOrderPo merOrderPo) {
        String tradeNo=IDUtils.genIdStr("D");
        List<MerOrder> merOrderList=new ArrayList<MerOrder>();
        List<MerPaySeqPo> merPaySeqPos= merOrderPo.getOrderList();
        BigDecimal tradeAmt=BigDecimal.ZERO;
        java.util.Date nowDate=new java.util.Date();

        for(MerPaySeqPo merPaySeqPo:merPaySeqPos){
            MerOrder merOrder=new MerOrder();
            merOrder.setMerNo(merChannelInfo.getMerNo());
            merOrder.setMerName(merChannelInfo.getMerName());
            merOrder.setEquipIp(merOrderPo.getEquipIp());
            merOrder.setEquipType(merOrderPo.getEquipType());
            merOrder.setEquipNo(merOrderPo.getEquipNo());
            merOrder.setMerOrderNo(merPaySeqPo.getOrderNo());
            merOrder.setMerPaySeq(merPaySeqPo.getMerPaySeq());
            merOrder.setPayNo(merPaySeqPo.getOrderNo()+"_"+merPaySeqPo.getMerPaySeq());
            merOrder.setPayTime(new java.sql.Timestamp(nowDate.getTime()));
            merOrder.setProductNo(merOrderPo.getProductNo());
            merOrder.setPayAmt(new BigDecimal(merOrderPo.getPayAmt()));
            merOrder.setOrderAmt(new BigDecimal(merOrderPo.getOrderAmt()));
            merOrder.setDiscountAmt(new BigDecimal(merOrderPo.getDiscountAmt()));
            merOrder.setUserNo(merOrderPo.getUserNo());
            merOrder.setResv1(merOrderPo.getResv1());
            merOrder.setResv2(merOrderPo.getResv2());
            merOrder.setResv3(merOrderPo.getResv3());
            merOrder.setTradeNo(tradeNo);
            tradeAmt=tradeAmt.add(new BigDecimal(merOrderPo.getPayAmt()));
            merOrder.setCreateTime(new java.sql.Timestamp(nowDate.getTime()));
            merOrder.setModiTime(new java.sql.Timestamp(nowDate.getTime()));
            merOrderList.add(merOrder);
        }
        merOrderRep.saveAll(merOrderList);
        PayTradeTotal payTradeTotal=new PayTradeTotal();
        payTradeTotal.setTradeNo(tradeNo);
        payTradeTotal.setTradeAmt(tradeAmt);
        payTradeTotal.setMerNo(merChannelInfo.getMerNo());
        payTradeTotal.setMerName(merChannelInfo.getMerName());
        payTradeTotal.setTradeTime(new java.sql.Timestamp(nowDate.getTime()));
        payTradeTotal.setUserNo(merOrderPo.getUserNo());
        payTradeTotal.setTradeType(TradeTypeEnum.PAY.getCode());
        payTradeTotal.setTradeStatus(TradeStatusEnum.INIT.getCode());
        payTradeTotal.setCreateTime(new java.sql.Timestamp(nowDate.getTime()));
        payTradeTotal.setModiTime(new java.sql.Timestamp(nowDate.getTime()));
        payTradeTotalRep.saveAndFlush(payTradeTotal);
        PayOrderDetail payOrderDetail=new PayOrderDetail();
        payOrderDetail.setTradeNo(tradeNo);
        payOrderDetail.setPayTime(new java.sql.Timestamp(nowDate.getTime()));
        String paySeqNo=IDUtils.genIdStr("S");
        payOrderDetail.setPaySeqNo(paySeqNo);
        payOrderDetail.setPayStatus(PayStatusEnum.PAY_INIT.getCode());
        payOrderDetail.setPayAmt(tradeAmt);
        payOrderDetail.setUserNo(merOrderPo.getUserNo());
        payOrderDetail.setMerNo(merChannelInfo.getMerNo());
        payOrderDetail.setMerName(merChannelInfo.getMerName());
        payOrderDetail.setPayWay(merChannelInfo.getPayWay());
        payOrderDetail.setChannelCode(merChannelInfo.getChannelCode());
        payOrderDetail.setChannelName(merChannelInfo.getChannelName());
        payOrderDetail.setChannelPartenerNo(merChannelInfo.getChannelPartenerNo());
        payOrderDetail.setChannelPartenerName(merChannelInfo.getChannelPartenerName());
        payOrderDetail.setGoodsName(merOrderPo.getGoodsName());
        payOrderDetail.setGoodsDesc(merOrderPo.getGoodsName());
        payOrderDetail.setCurrencyType("CNY");
        payOrderDetail.setPayFee(merChannelInfo.getChannelFee());
        payOrderDetail.setStartTime(new java.sql.Timestamp(nowDate.getTime()));//订单开始时间
        Date endDate=calEndTime(nowDate,merOrderPo.getTimeOutExpress());
        payOrderDetail.setEndTime(new java.sql.Timestamp(endDate.getTime()));//订单失效时间
        payOrderDetail.setEquipIp(merOrderPo.getEquipIp());
        payOrderDetail.setEquipType(merOrderPo.getEquipType());
        payOrderDetail.setEquipNo(merOrderPo.getEquipNo());
        payOrderDetail.setNotifyUrl(commonReqVo.getNotifyUrl());
        payOrderDetail.setForwardUrl(commonReqVo.getForwardUrl());
        payOrderDetail.setCreateTime(new java.sql.Timestamp(nowDate.getTime()));
        payOrderDetail.setModiTime(new java.sql.Timestamp(nowDate.getTime()));
        payOrderDetailRep.saveAndFlush(payOrderDetail);
        merChannelInfo.setInnerTradeNo(tradeNo);
        merChannelInfo.setInnerSeqNo(paySeqNo);
        merChannelInfo.setTradeType(TradeTypeEnum.PAY.getCode());
    }

    /**
     * 计算订单失效时间
     * @param nowDate
     * @param timeOutExpress
     * @return
     */
    private Date calEndTime(Date nowDate,String timeOutExpress) {
        //根据失效时间 计算结束时间
        int seconds=30*60;//默认30分钟关闭
        Date endDate= DateUtil.addDate(nowDate,seconds);
        if(StringUtils.isNotEmpty(timeOutExpress)){
            String endStr=timeOutExpress.substring(timeOutExpress.length()-1,timeOutExpress.length());
            String startStr=timeOutExpress.substring(0,timeOutExpress.length()-1);
            int startNum=Integer.valueOf(startStr).intValue();
            if("m".equals(endStr)){
                seconds=startNum*60;
            }else if("h".equals(endStr)){
                seconds=startNum*60*60;
            }else if("d".equals(endStr)){
                seconds=startNum*60*60*24;
            }
            endDate=DateUtil.addDate(nowDate,seconds);
            if("1c".equals(endStr)){
                endDate=DateUtil.getNowDateEnd();
            }
        }
        return endDate;
    }

    @Override
    @Transactional
    public void notifyOrder(NotifyPayParamPo notifyPayParamPo) {
        PayOrderDetail payOrderDetail=new PayOrderDetail();
        payOrderDetail.setPaySeqNo(notifyPayParamPo.getPaySeqNo());
        payOrderDetail.setRespPayNo(notifyPayParamPo.getThirdTradeNo());
        payOrderDetail.setPayStatus(notifyPayParamPo.getTradeStatus());
        PayOrderDetail oldPayOrderDetail=payOrderDetailRep.findByPaySeqNo(payOrderDetail.getPaySeqNo());
        if(oldPayOrderDetail==null||PayStatusEnum.PAY_INIT.getCode()!=oldPayOrderDetail.getPayStatus()){
            logger.info("[通知修改订单]状态不是处理中,参数:{}",notifyPayParamPo);
            return;
        }
        java.util.Date nowDate=new java.util.Date();
        if(PayStatusEnum.PAY_SUCCESS.getCode()==payOrderDetail.getPayStatus()){
            payOrderDetail.setPaySuccessTime(new java.sql.Timestamp(notifyPayParamPo.getThirdPayTime().getTime()));
        }
        payOrderDetail.setSellerAcct(notifyPayParamPo.getSellerId());
        payOrderDetail.setPaylerAcct(notifyPayParamPo.getBuyllerId());
        payOrderDetail.setBuyInvoiceAmt(notifyPayParamPo.getBuyInvoiceAmt());
        payOrderDetail.setMerRecvAmt(notifyPayParamPo.getMerRecvAmt());
        payOrderDetail.setThirdDiscountAmt(notifyPayParamPo.getThirdDiscountAmt());
        payOrderDetail.setBuyPayAmt(notifyPayParamPo.getBuyPyAmt());
        payOrderDetail.setModiTime(new java.sql.Timestamp(nowDate.getTime()));
        payOrderDetailRep.updateByPaySeqNo(
                payOrderDetail.getPayStatus(),
                payOrderDetail.getModiTime(),
                payOrderDetail.getPaySuccessTime(),
                payOrderDetail.getSellerAcct(),
                payOrderDetail.getPaylerAcct(),
                payOrderDetail.getRespPayNo(),
                payOrderDetail.getMerRecvAmt(),
                payOrderDetail.getThirdDiscountAmt(),
                payOrderDetail.getBuyInvoiceAmt(),
                payOrderDetail.getBuyPayAmt(),
                payOrderDetail.getPaySeqNo()
        );

        PayTradeTotal payTradeTotal=new PayTradeTotal();
        payTradeTotal.setTradeNo(oldPayOrderDetail.getTradeNo());
        TradeStatusEnum tradeStatusEnum=TradeStatusEnum.parse(String.valueOf(notifyPayParamPo.getTradeStatus()));
        payTradeTotal.setTradeStatus(tradeStatusEnum.getCode());
        payTradeTotal.setModiTime(new java.sql.Timestamp(nowDate.getTime()) );
        payTradeTotalRep.updateByTradeNo(payTradeTotal.getTradeStatus(),payTradeTotal.getModiTime(),payTradeTotal.getTradeNo());
        MerOrder merOrder=new MerOrder();
        merOrder.setTradeNo(oldPayOrderDetail.getTradeNo());
        merOrder.setPayStatus(notifyPayParamPo.getTradeStatus());
        merOrder.setModiTime(new java.sql.Timestamp(nowDate.getTime()));
        merOrderRep.updateByTradeNo(merOrder.getPayStatus(),merOrder.getModiTime(),merOrder.getTradeNo());
    }

    @Override
    @Transactional
    public void updateOrder( PayOrderDetail payOrderDetail) {
        java.util.Date nowDate=new java.util.Date();
        payOrderDetail.setModiTime(new java.sql.Timestamp(nowDate.getTime()) );
        PayOrderDetail oldPayOrderDetail=payOrderDetailRep.findByPaySeqNo(payOrderDetail.getPaySeqNo());
        if(PayStatusEnum.PAY_INIT.getCode()!=oldPayOrderDetail.getPayStatus()){
            logger.info("[修改订单]状态不是处理中,参数:{}",payOrderDetail);
            return;
        }
        payOrderDetailRep.updateByPaySeqNo(
                payOrderDetail.getPayStatus(),
                payOrderDetail.getModiTime(),
                payOrderDetail.getPaySuccessTime(),
                payOrderDetail.getSellerAcct(),
                payOrderDetail.getPaylerAcct(),
                payOrderDetail.getRespPayNo(),
                payOrderDetail.getMerRecvAmt(),
                payOrderDetail.getThirdDiscountAmt(),
                payOrderDetail.getBuyInvoiceAmt(),
                payOrderDetail.getBuyPayAmt(),
                payOrderDetail.getPaySeqNo()
        );

        PayTradeTotal payTradeTotal=new PayTradeTotal();
        payTradeTotal.setTradeNo(oldPayOrderDetail.getTradeNo());
        TradeStatusEnum tradeStatusEnum=TradeStatusEnum.parse(String.valueOf(payOrderDetail.getPayStatus()));
        payTradeTotal.setTradeStatus(tradeStatusEnum.getCode());
        payTradeTotal.setModiTime(new java.sql.Timestamp(nowDate.getTime()) );
        payTradeTotalRep.updateByTradeNo(payTradeTotal.getTradeStatus(),payTradeTotal.getModiTime(),payTradeTotal.getTradeNo());
        MerOrder merOrder=new MerOrder();
        merOrder.setTradeNo(oldPayOrderDetail.getTradeNo());
        merOrder.setPayStatus(payOrderDetail.getPayStatus());
        merOrder.setModiTime(new java.sql.Timestamp(nowDate.getTime()));
        merOrderRep.updateByTradeNo(merOrder.getPayStatus(),merOrder.getModiTime(),merOrder.getTradeNo());
    }

    @Override
    public PayOrderDetail queryOrder(MerOrderQueryPo merOrderQueryPo) {
        List<MerPaySeqPo> merPaySeqPos= merOrderQueryPo.getOrderList();
        List<String> merOrderConds=new ArrayList<String>();
        for(MerPaySeqPo merPaySeqPo:merPaySeqPos){
            String uniqNo=merPaySeqPo.getOrderNo()+"_"+merPaySeqPo.getMerPaySeq();
            merOrderConds.add(uniqNo);
        }
        List<MerOrder> merOrders= merOrderRep.findByPayNos(merOrderConds);
        if(merOrders==null || merOrders.size()<=0) return null;
        String tradeNo=merOrders.get(0).getTradeNo();
        List<PayOrderDetail> payOrderDetails=payOrderDetailRep.findByTradeNo(tradeNo);
        if(payOrderDetails==null || payOrderDetails.size()<=0) return null;
        return payOrderDetails.get(0);
    }
}
