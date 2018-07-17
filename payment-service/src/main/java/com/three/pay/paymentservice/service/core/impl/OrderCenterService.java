package com.three.pay.paymentservice.service.core.impl;

import com.alibaba.fastjson.JSONObject;
import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentcommon.dto.MerChannelInfo;
import com.three.pay.paymentcommon.enums.PayStatusEnum;
import com.three.pay.paymentcommon.enums.TradeStatusEnum;
import com.three.pay.paymentcommon.enums.TradeTypeEnum;
import com.three.pay.paymentcommon.po.MerOrderQueryPo;
import com.three.pay.paymentcommon.po.MerPaySeqPo;
import com.three.pay.paymentcommon.po.MerUnionOrderPo;
import com.three.pay.paymentcommon.po.notify.NotifyPayParamPo;
import com.three.pay.paymentcommon.utils.DateUtil;
import com.three.pay.paymentcommon.utils.HttpClientUtil;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    //创建固定线程池 可根据应用及机器实际情况进行调整
    ExecutorService executorService= Executors.newFixedThreadPool(1);
    @Override
    @Transactional
    public void createOrder(MerChannelInfo merChannelInfo, CommonReqParam commonReqVo, MerUnionOrderPo merUnionOrderPo) {
        String tradeNo=IDUtils.genIdStr("D");
        List<MerOrder> merOrderList=new ArrayList<MerOrder>();
        List<MerPaySeqPo> merPaySeqPos= merUnionOrderPo.getOrderList();
        BigDecimal tradeAmt=BigDecimal.ZERO;
        java.util.Date nowDate=new java.util.Date();

        for(MerPaySeqPo merPaySeqPo:merPaySeqPos){
            MerOrder merOrder=new MerOrder();
            merOrder.setMerNo(merChannelInfo.getMerNo());
            merOrder.setMerName(merChannelInfo.getMerName());
            merOrder.setEquipIp(merUnionOrderPo.getEquipIp());
            merOrder.setEquipType(merUnionOrderPo.getEquipType());
            merOrder.setEquipNo(merUnionOrderPo.getEquipNo());
            merOrder.setMerOrderNo(merPaySeqPo.getMerOrderNo());
            merOrder.setMerPaySeq(merPaySeqPo.getMerPaySeq());
            merOrder.setPayNo(merPaySeqPo.getMerOrderNo()+"_"+merPaySeqPo.getMerPaySeq());
            merOrder.setPayTime(new java.sql.Timestamp(nowDate.getTime()));
            merOrder.setProductNo(merUnionOrderPo.getProductNo());
            merOrder.setPayAmt(new BigDecimal(merUnionOrderPo.getPayAmt()));
            merOrder.setOrderAmt(new BigDecimal(merUnionOrderPo.getOrderAmt()));
            merOrder.setDiscountAmt(new BigDecimal(merUnionOrderPo.getDiscountAmt()));
            merOrder.setUserNo(merUnionOrderPo.getUserNo());
            merOrder.setResv1(merUnionOrderPo.getResv1());
            merOrder.setResv2(merUnionOrderPo.getResv2());
            merOrder.setResv3(merUnionOrderPo.getResv3());
            merOrder.setTradeNo(tradeNo);
            merOrder.setNotifyUrl(commonReqVo.getNotifyUrl());
            merOrder.setForwardUrl(commonReqVo.getForwardUrl());
            tradeAmt=tradeAmt.add(new BigDecimal(merUnionOrderPo.getPayAmt()));
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
        payTradeTotal.setUserNo(merUnionOrderPo.getUserNo());
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
        payOrderDetail.setUserNo(merUnionOrderPo.getUserNo());
        payOrderDetail.setMerNo(merChannelInfo.getMerNo());
        payOrderDetail.setMerName(merChannelInfo.getMerName());
        payOrderDetail.setPayWay(merChannelInfo.getPayWay());
        payOrderDetail.setChannelCode(merChannelInfo.getChannelCode());
        payOrderDetail.setChannelName(merChannelInfo.getChannelName());
        payOrderDetail.setChannelPartenerNo(merChannelInfo.getChannelPartenerNo());
        payOrderDetail.setChannelPartenerName(merChannelInfo.getChannelPartenerName());
        payOrderDetail.setGoodsName(merUnionOrderPo.getGoodsName());
        payOrderDetail.setGoodsDesc(merUnionOrderPo.getGoodsName());
        payOrderDetail.setCurrencyType("CNY");
        payOrderDetail.setPayFee(merChannelInfo.getChannelFee());
        payOrderDetail.setStartTime(new java.sql.Timestamp(nowDate.getTime()));//订单开始时间
        Date endDate=calEndTime(nowDate, merUnionOrderPo.getTimeOutExpress());
        payOrderDetail.setEndTime(new java.sql.Timestamp(endDate.getTime()));//订单失效时间
        payOrderDetail.setEquipIp(merUnionOrderPo.getEquipIp());
        payOrderDetail.setEquipType(merUnionOrderPo.getEquipType());
        payOrderDetail.setEquipNo(merUnionOrderPo.getEquipNo());
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

        //通知给订单系统
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                notifyOrderSystem(oldPayOrderDetail.getTradeNo());
            }
        });
    }

    /**
     * 异步通知订单系统结果
     * @param tradeNo
     */
    private void notifyOrderSystem(String tradeNo) {
       List<MerOrder> merOrders = merOrderRep.findByTradeNo(tradeNo);
       for(MerOrder merOrder:merOrders){
           JSONObject notifyParam=new JSONObject();
           notifyParam.put("orderNo",merOrder.getMerOrderNo());
           notifyParam.put("paySeqNo",merOrder.getMerPaySeq());
           notifyParam.put("respCode","00");
           notifyParam.put("respMsg","成功");
           notifyParam.put("payTradeNo",merOrder.getTradeNo());
           if(PayStatusEnum.PAY_SUCCESS.getCode()==merOrder.getPayStatus()){
               notifyParam.put("payResult","PAY_SUCCESS");//成功
           }else{
               notifyParam.put("payResult","PAY_FAIL");//失败
           }
           notifyParam.put("paySuccessTime",merOrder.getModiTime());
           notifyParam.put("signValue",merOrder.getMerOrderNo());
           logger.info("[通知订单系统]通知参数:{}",notifyParam);
           String respMsg=HttpClientUtil.doPost(merOrder.getNotifyUrl(),notifyParam);
           if("00".equals(respMsg)){
               merOrder.setNotifyStatus(1);
           }else{
               merOrder.setNotifyStatus(0);
           }
           merOrder.setModiTime(new java.sql.Timestamp(new Date().getTime()));
           merOrder.setNotifyNum(merOrder.getNotifyNum()+1);
           merOrder.setNotifyMsg(respMsg);
           merOrderRep.updateByTradeNo(merOrder.getPayStatus(),merOrder.getModiTime(),merOrder.getTradeNo());
       }

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
            String uniqNo=merPaySeqPo.getMerOrderNo()+"_"+merPaySeqPo.getMerPaySeq();
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
