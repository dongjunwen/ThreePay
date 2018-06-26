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
import com.three.pay.paymentcommon.enums.ChannelCodeEnum;
import com.three.pay.paymentcommon.enums.RefundStatusEnum;
import com.three.pay.paymentcommon.po.MerPaySeqPo;
import com.three.pay.paymentcommon.po.MerRefundQueryPo;
import com.three.pay.paymentjdbc.entity.MerOrder;
import com.three.pay.paymentjdbc.entity.MerRefundOrder;
import com.three.pay.paymentjdbc.entity.PayOrderDetail;
import com.three.pay.paymentjdbc.entity.PayRefundDetail;
import com.three.pay.paymentservice.service.channel.IChannelService;
import com.three.pay.paymentservice.service.core.*;
import com.three.pay.paymentservice.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:luiz
 * @Date: 2018/6/8 13:22
 * @Descripton:退款查询接口
 * @Modify :
 **/
@Service
public class MerRefundQueryImpl implements ITradeProcess {

    private static final Logger logger= LoggerFactory.getLogger(MerRefundQueryImpl.class);
    @Autowired
    IChannelRouteCenter iChannelRouteCenter;
    @Autowired
    IMerRefund iMerRefund;
    @Autowired
    IPayOrderDetail iPayOrderDetail;
    @Autowired
    IMerOrder iMerOrder;
    @Autowired
    IChannelService iChannelService;
    @Autowired
    IRefundCenter iRefundCenter;
    @Autowired
    IPayRefundDetail iPayRefundDetail;

    @Override
    public boolean isSupport(ServiceNameEnum serviceNameEnum) {
        return ServiceNameEnum.QUERY_REFUND.equals(serviceNameEnum);
    }

    @Override
    public PayResult execute(CommonReqParam commonReqVo) {
        PayResult payResult=PayResult.newSuccess();
        try{
            //1.参数校验
            String reqContent=commonReqVo.getReqContent();
            MerRefundQueryPo merRefundQueryPo= JSON.parseObject(reqContent, MerRefundQueryPo.class);
            ValidatorUtil.validateEntity(merRefundQueryPo);
            //3.先查询数据库
            MerRefundOrder merRefundOrder=iMerRefund.findByRefundNo(merRefundQueryPo.getRefundNo());
            if(merRefundOrder==null){
                throw new BusinessException(ResultCode.COMMON_DATA_NOT_EXISTS);
            }

            String respJson= JSONObject.toJSONString(merRefundOrder);
            if(RefundStatusEnum.REFUND_INIT.getCode()==merRefundOrder.getRefundStatus()){
                //4.执行渠道层动作
                MerPaySeqPo merPaySeqPo=new MerPaySeqPo();
                merPaySeqPo.setMerOrderNo(merRefundOrder.getMerOrderNo());
                merPaySeqPo.setMerPaySeq(merRefundOrder.getMerPaySeq());
                MerOrder merOrder=iMerOrder.queryOrder(merPaySeqPo);
                PayOrderDetail payOrderDetail=iPayOrderDetail.findByTradeNo(merOrder.getTradeNo());
                MerChannelInfo merChannelInfo =iChannelRouteCenter.buildMerChannelInfo(payOrderDetail, ChannelActionEnum.REFUND_QUERY);
                PayRefundDetail oldPayRefundDetail=iPayRefundDetail.findByTradeNo(merRefundOrder.getTradeNo());
                merChannelInfo.setInnerRefundSeqNo(oldPayRefundDetail.getRefundSeqNo());
                ChannelRespParam channelRespParam=iChannelService.channelProcess(merChannelInfo,commonReqVo);
                if(ChannelCodeEnum.SUCCESS.getCode().equals(channelRespParam.getRespCode())) {
                    respJson = channelRespParam.getRespContent();
                    //更新
                    PayRefundDetail payRefundDetail = JSONObject.parseObject(respJson, PayRefundDetail.class);
                    iRefundCenter.updateRefund(payRefundDetail);
                }else{
                    payResult.setError(channelRespParam.getRespCode(),channelRespParam.getRespMsg());
                    return payResult;
                }
            }
            //5.返回结果
            payResult.setData(respJson);
        }catch (BusinessException be){
            logger.error("【退款统一查询】请求参数:{},发生业务异常:{}",commonReqVo,be);
            payResult.setError(be.getCode(),be.getMsg());
        } catch (Exception e){
            logger.error("【退款统一查询】请求参数:{},发生异常:{}",commonReqVo,e);
            payResult.setError(ResultCode.FAIL.getCode(),e.getMessage());
        }
        return payResult;
    }
}
