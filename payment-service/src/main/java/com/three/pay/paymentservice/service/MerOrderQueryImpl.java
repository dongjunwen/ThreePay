package com.three.pay.paymentservice.service;

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
import com.three.pay.paymentcommon.enums.PayStatusEnum;
import com.three.pay.paymentcommon.po.MerOrderQueryPo;
import com.three.pay.paymentjdbc.entity.PayOrderDetail;
import com.three.pay.paymentservice.service.channel.IChannelService;
import com.three.pay.paymentservice.service.core.IChannelRouteCenter;
import com.three.pay.paymentservice.service.core.IOrderCenter;
import com.three.pay.paymentservice.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:luiz
 * @Date: 2018/5/25 16:38
 * @Descripton: 订单查询统一入口
 * @Modify :
 **/
@Service
public class MerOrderQueryImpl implements ITradeProcess {
    private static final Logger logger= LoggerFactory.getLogger(MerOrderQueryImpl.class);
    @Autowired
    IChannelRouteCenter iChannelRouteCenter;
    @Autowired
    IOrderCenter iOrderCenter;
    @Autowired
    IChannelService iChannelService;


    @Override
    public boolean isSupport(ServiceNameEnum serviceNameEnum) {
        return ServiceNameEnum.QUERY_ORDER.equals(serviceNameEnum);
    }

    @Override
    public PayResult execute(CommonReqParam commonReqVo) {
        PayResult payResult=PayResult.newSuccess();
        try{
            //1.参数校验
            String reqContent=commonReqVo.getReqContent();
            MerOrderQueryPo merOrderQueryPo= JSON.parseObject(reqContent, MerOrderQueryPo.class);
            ValidatorUtil.validateEntity(merOrderQueryPo);
            //3.先查询数据库
            PayOrderDetail payOrderDetail=iOrderCenter.queryOrder(merOrderQueryPo);
            if(payOrderDetail==null){
                throw new BusinessException(ResultCode.COMMON_DATA_NOT_EXISTS);
            }
            String respJson=JSONObject.toJSONString(payOrderDetail);
             if(PayStatusEnum.PAY_INIT.getCode()==payOrderDetail.getPayStatus()){
                 //4.执行渠道层动作
                 MerChannelInfo merChannelInfo =iChannelRouteCenter.buildMerChannelInfo(payOrderDetail,ChannelActionEnum.ORDER_QEURY);
                 ChannelRespParam channelRespParam=iChannelService.channelProcess(merChannelInfo,commonReqVo);
                 if(ChannelCodeEnum.SUCCESS.getCode().equals(channelRespParam.getRespCode())){
                     respJson=channelRespParam.getRespContent();
                     //更新
                     PayOrderDetail payOrderDetail1=JSONObject.parseObject(respJson,PayOrderDetail.class);
                     iOrderCenter.updateOrder(payOrderDetail1);
                 }else if(ChannelCodeEnum.TRADE_NOT_EXIST.getCode().equals(channelRespParam.getRespCode())){
                     respJson=channelRespParam.getRespContent();
                     //更新,关闭改订单
                     PayOrderDetail payOrderDetail1=JSONObject.parseObject(respJson,PayOrderDetail.class);
                     iOrderCenter.updateOrder(payOrderDetail1);
                 }else{
                     payResult.setError(channelRespParam.getRespCode(),channelRespParam.getRespMsg());
                     return payResult;
                 }
             }
            //5.返回结果
            payResult.setData(respJson);
        }catch (BusinessException be){
            logger.error("【订单统一查询】请求参数:{},发生业务异常:{}",commonReqVo,be);
            payResult.setError(be.getCode(),be.getMsg());
        } catch (Exception e){
            logger.error("【订单统一查询】请求参数:{},发生异常:{}",commonReqVo,e);
            payResult.setError(ResultCode.FAIL.getCode(),e.getMessage());
        }
        return payResult;
    }

}
