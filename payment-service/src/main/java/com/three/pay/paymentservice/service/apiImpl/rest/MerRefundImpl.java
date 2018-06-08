package com.three.pay.paymentservice.service.apiImpl.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.three.pay.paymentapi.api.ITradeProcess;
import com.three.pay.paymentapi.enums.ResultCode;
import com.three.pay.paymentapi.enums.ServiceNameEnum;
import com.three.pay.paymentapi.except.BusinessException;
import com.three.pay.paymentapi.result.PayResult;
import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentchannel.param.ChannelRespParam;
import com.three.pay.paymentcommon.dto.MerChannelInfo;
import com.three.pay.paymentcommon.enums.ChannelActionEnum;
import com.three.pay.paymentcommon.enums.PayStatusEnum;
import com.three.pay.paymentcommon.enums.RefundStatusEnum;
import com.three.pay.paymentcommon.po.MerPaySeqPo;
import com.three.pay.paymentcommon.po.MerRefundOrderPo;
import com.three.pay.paymentjdbc.entity.*;
import com.three.pay.paymentservice.service.channel.IChannelService;
import com.three.pay.paymentservice.service.core.*;
import com.three.pay.paymentservice.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author:luiz
 * @Date: 2018/6/8 13:22
 * @Descripton:退款发起
 * @Modify :
 **/
@Service
public class MerRefundImpl implements ITradeProcess {
    @Autowired
    IMerOrder iMerOrder;
    @Autowired
    IMerRefund iMerRefund;
    @Autowired
    IPayOrderDetail iPayOrderDetail;
    @Autowired
    IChannelDetail iChannelDetail;
    @Autowired
    IRefundCenter iRefundCenter;
    @Autowired
    IPayReqLog iPayReqLog;
    @Autowired
    IChannelService iChannelService;


    private static final Logger logger= LoggerFactory.getLogger(MerRefundImpl.class);
    @Override
    public boolean isSupport(ServiceNameEnum serviceNameEnum) {
        return ServiceNameEnum.REFUND_ORDER.equals(serviceNameEnum);
    }

    @Override
    public PayResult execute(CommonReqParam commonReqVo) {
        PayResult payResult=PayResult.newSuccess();
        try{
            //1.参数校验
            MerChannelInfo merChannelInfo =new MerChannelInfo();
            MerRefundOrderPo merRefundOrderPo =validParam(commonReqVo,merChannelInfo);
            //2.记录退款流水
            iRefundCenter.createRefund(merChannelInfo,commonReqVo,merRefundOrderPo);
            //3.执行渠道层动作 发起退款
            ChannelRespParam channelRespParam=iChannelService.channelProcess(merChannelInfo,commonReqVo);
            //4.退款后执行更新操作
            PayRefundDetail payRefundDetail=JSONObject.parseObject(channelRespParam.getRespContent(), PayRefundDetail.class);
            iRefundCenter.updateRefund(payRefundDetail);
            //执行同步请求存储动作
            iPayReqLog.save(channelRespParam, merChannelInfo);
            //5.返回结果
        }catch (BusinessException be){
            logger.error("【发起退款】请求参数:{},发生业务异常:{}",commonReqVo,be);
            payResult.setError(be.getCode(),be.getMsg());
        } catch (Exception e){
            logger.error("【发起退款】请求参数:{},发生异常:{}",commonReqVo,e);
            payResult.setError(ResultCode.FAIL.getCode(),e.getMessage());
        }
        return payResult;
    }



    /**
     *  参数校验，数据校验
     *  付款校验 是否已付款
     *  退款校验 是否多退
     * @param commonReqVo
     */
    private MerRefundOrderPo validParam(CommonReqParam commonReqVo, MerChannelInfo merChannelInfo) {
        String reqContent=commonReqVo.getReqContent();
        MerRefundOrderPo merRefundOrderPo = JSON.parseObject(reqContent, MerRefundOrderPo.class);
        ValidatorUtil.validateEntity(merRefundOrderPo);

        MerPaySeqPo merPaySeqPo=new MerPaySeqPo();
        merPaySeqPo.setMerOrderNo(merRefundOrderPo.getMerOrderNo());
        merPaySeqPo.setMerPaySeq(merRefundOrderPo.getMerPaySeq());
        MerOrder merOrder= iMerOrder.queryOrder(merPaySeqPo);
        if(merOrder==null){
            throw new BusinessException(ResultCode.COMMON_DATA_NOT_EXISTS);
        }
        long payStatus=merOrder.getPayStatus();
        if(payStatus!= PayStatusEnum.PAY_SUCCESS.getCode()){
            throw new BusinessException(ResultCode.ORDER_NOT_PAY);
        }
        BigDecimal payAmt=merOrder.getPayAmt();
        BigDecimal refundAmt=new BigDecimal(merRefundOrderPo.getRefundAmt());
        if (refundAmt.compareTo(payAmt)==1){
            throw new BusinessException(ResultCode.REFUND_MORE_THAN_PAY);
        }
        List<MerRefundOrder> merRefundOrders = iMerRefund.queryOrder(merPaySeqPo);
        BigDecimal actRefundAmt=BigDecimal.ZERO;
        if(merRefundOrders!=null && merRefundOrders.size()>=1){
            for(MerRefundOrder merRefundOrder:merRefundOrders){
                if(RefundStatusEnum.REFUND_FAIL.getCode()!=merRefundOrder.getRefundStatus()){
                    actRefundAmt=actRefundAmt.add(merRefundOrder.getRefundAmt());
                }
            }
        }
        actRefundAmt=actRefundAmt.add(refundAmt);
        if (actRefundAmt.compareTo(payAmt)==1){
            throw new BusinessException(ResultCode.REFUND_OVER_THAN_PAY);
        }
        merChannelInfo.setUserNo(merOrder.getUserNo());
        merChannelInfo.setProductNo(merOrder.getProductNo());
        merChannelInfo.setMerNo(merOrder.getMerNo());
        merChannelInfo.setMerName(merOrder.getMerName());
        merChannelInfo.setPayNo(merOrder.getPayNo());
        PayOrderDetail payOrderDetail=iPayOrderDetail.findByTradeNo(merOrder.getTradeNo());
        merChannelInfo.setInnerSeqNo(payOrderDetail.getPaySeqNo());
        merChannelInfo.setInnerTradeNo(payOrderDetail.getTradeNo());
        merChannelInfo.setThirdSeqNo(payOrderDetail.getRespPayNo());
        merChannelInfo.setChannelAction(ChannelActionEnum.REFUND.getCode());
        merChannelInfo.setChannelCode(payOrderDetail.getChannelCode());
        merChannelInfo.setChannelName(payOrderDetail.getChannelName());
        merChannelInfo.setChannelPartenerNo(payOrderDetail.getChannelPartenerNo());
        merChannelInfo.setChannelPartenerName(payOrderDetail.getChannelPartenerName());
        ChannelDetail channelDetail=iChannelDetail.findByPayWay(payOrderDetail.getPayWay());
        merChannelInfo.setPayWay(channelDetail.getPayWay());
        merChannelInfo.setPayWayName(channelDetail.getPayWayName());
        merChannelInfo.setChannelFee(channelDetail.getChannelFee());
        merChannelInfo.setSignType(channelDetail.getSignType());
        merChannelInfo.setPrivKey(channelDetail.getPrivKey());
        merChannelInfo.setPubKey(channelDetail.getPubKey());
        merChannelInfo.setResvKey(channelDetail.getResvKey());
        merChannelInfo.setResv1(channelDetail.getResv1());
        merChannelInfo.setResv2(channelDetail.getResv2());
        return merRefundOrderPo;
    }
}
