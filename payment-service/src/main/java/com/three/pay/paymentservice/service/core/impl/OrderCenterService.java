package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentcommon.dto.MerOrderDto;
import com.three.pay.paymentcommon.enums.PayStatusEnum;
import com.three.pay.paymentcommon.enums.TradeStatusEnum;
import com.three.pay.paymentcommon.enums.TradeTypeEnum;
import com.three.pay.paymentcommon.po.MerOrderPo;
import com.three.pay.paymentcommon.po.MerPaySeqPo;
import com.three.pay.paymentcommon.po.notify.NotifyPayParamPo;
import com.three.pay.paymentcommon.utils.IDUtils;
import com.three.pay.paymentjdbc.entity.MerOrder;
import com.three.pay.paymentjdbc.entity.PayOrderDetail;
import com.three.pay.paymentjdbc.entity.PayTradeTotal;
import com.three.pay.paymentjdbc.respository.MerOrderRep;
import com.three.pay.paymentjdbc.respository.PayOrderDetailRep;
import com.three.pay.paymentjdbc.respository.PayTradeTotalRep;
import com.three.pay.paymentservice.service.core.IMerOrderDetail;
import com.three.pay.paymentservice.service.core.IOrderCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:luiz
 * @Date: 2018/5/28 15:20
 * @Descripton:
 * @Modify :
 **/
@Service
public class OrderCenterService implements IOrderCenter {
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
    public void createOrder(MerOrderDto merOrderDto, CommonReqParam commonReqVo, MerOrderPo merOrderPo) {
        String tradeNo=IDUtils.genIdStr("D");
        List<MerOrder> merOrderList=new ArrayList<MerOrder>();
        List<MerPaySeqPo> merPaySeqPos= merOrderPo.getOrderList();
        BigDecimal tradeAmt=BigDecimal.ZERO;
        java.util.Date nowDate=new java.util.Date();

        for(MerPaySeqPo merPaySeqPo:merPaySeqPos){
            MerOrder merOrder=new MerOrder();
            merOrder.setMerNo(merOrderDto.getMerNo());
            merOrder.setMerName(merOrderDto.getMerName());
            merOrder.setEquipIp(merOrderPo.getEquipIp());
            merOrder.setEquipType(merOrderPo.getEquipType());
            merOrder.setEquipNo(merOrderPo.getEquipNo());
            merOrder.setMerOrderNo(merPaySeqPo.getOrderNo());
            merOrder.setMerPaySeq(merPaySeqPo.getMerPaySeq());
            merOrder.setPayNo(merPaySeqPo.getOrderNo()+"_"+merPaySeqPo.getMerPaySeq());
            merOrder.setPayTime(new java.sql.Timestamp(nowDate.getTime()));
            merOrder.setProductNo(commonReqVo.getProductNo());
            merOrder.setPayAmt(new BigDecimal(merOrderPo.getPayAmt()));
            merOrder.setOrderAmt(new BigDecimal(merOrderPo.getOrderAmt()));
            merOrder.setDiscountAmt(new BigDecimal(merOrderPo.getDiscountAmt()));
            merOrder.setUserNo(merOrderPo.getUserNo());
            merOrder.setResv1(merOrderPo.getResv1());
            merOrder.setResv2(merOrderPo.getResv2());
            merOrder.setResv3(merOrderPo.getResv3());
            merOrder.setTradeNo(tradeNo);
            tradeAmt.add(new BigDecimal(merOrderPo.getPayAmt()));
            merOrder.setCreateTime(new java.sql.Timestamp(nowDate.getTime()));
            merOrder.setModiTime(new java.sql.Timestamp(nowDate.getTime()));
            merOrderList.add(merOrder);
        }
        merOrderRep.saveAll(merOrderList);
        PayTradeTotal payTradeTotal=new PayTradeTotal();
        payTradeTotal.setTradeNo(tradeNo);
        payTradeTotal.setTradeAmt(tradeAmt);
        payTradeTotal.setMerNo(merOrderDto.getMerNo());
        payTradeTotal.setMerName(merOrderDto.getMerName());
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
        payOrderDetail.setMerNo(merOrderDto.getMerNo());
        payOrderDetail.setMerName(merOrderDto.getMerName());
        payOrderDetail.setPayWay(merOrderDto.getPayWay());
        payOrderDetail.setChannelCode(merOrderDto.getChannelCode());
        payOrderDetail.setChannelName(merOrderDto.getChannelName());
        payOrderDetail.setChannelPartenerNo(merOrderDto.getChannelPartenerNo());
        payOrderDetail.setChannelPartenerName(merOrderDto.getChannelPartenerName());
        payOrderDetail.setGoodsName(merOrderPo.getGoodsName());
        payOrderDetail.setGoodsDesc(merOrderPo.getGoodsName());
        payOrderDetail.setCurrencyType("CNY");
        payOrderDetail.setPayFee(merOrderDto.getChannelFee());
        payOrderDetail.setStartTime(new java.sql.Timestamp(nowDate.getTime()));
        //payOrderDetail.setEndTime();//todo
        payOrderDetail.setEquipIp(merOrderPo.getEquipIp());
        payOrderDetail.setEquipType(merOrderPo.getEquipType());
        payOrderDetail.setEquipNo(merOrderPo.getEquipNo());
        //payOrderDetail.setForwardUrl(merOrderPo.get);
        payOrderDetail.setCreateTime(new java.sql.Timestamp(nowDate.getTime()));
        payOrderDetail.setModiTime(new java.sql.Timestamp(nowDate.getTime()));
        payOrderDetailRep.saveAndFlush(payOrderDetail);
        merOrderDto.setInnerTradeNo(tradeNo);
        merOrderDto.setInnerSeqNo(paySeqNo);
    }

    @Override
    public void notifyOrder(NotifyPayParamPo notifyPayParamPo) {
        PayOrderDetail payOrderDetail=new PayOrderDetail();
        payOrderDetail.setPaySeqNo(notifyPayParamPo.getPaySeqNo());
        payOrderDetail.setRespPayNo(notifyPayParamPo.getThirdTradeNo());
        payOrderDetail.setPayStatus(notifyPayParamPo.getTradeStatus());
        PayOrderDetail oldPayOrderDetail=payOrderDetailRep.findByPaySeqNo(payOrderDetail.getPaySeqNo());
        if(!TradeStatusEnum.INIT.getCode().equals(oldPayOrderDetail.getPayStatus())){
            return;
        }

        java.util.Date nowDate=new java.util.Date();
        if(PayStatusEnum.PAY_SUCCESS.getCode()==payOrderDetail.getPayStatus()){
            payOrderDetail.setPaySuccessTime(new java.sql.Timestamp(nowDate.getTime()));
        }
        payOrderDetail.setModiTime(new java.sql.Timestamp(nowDate.getTime()));
        payOrderDetailRep.updateByPaySeqNo(payOrderDetail.getPayStatus(),payOrderDetail.getModiTime(),payOrderDetail.getPaySuccessTime(),payOrderDetail.getPaySeqNo());

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
}