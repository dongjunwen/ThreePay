package com.three.pay.paymentservice.service.apiImpl.rest;

import com.alibaba.fastjson.JSON;
import com.three.pay.paymentapi.api.ITradeProcess;
import com.three.pay.paymentapi.enums.ResultCode;
import com.three.pay.paymentapi.enums.ServiceNameEnum;
import com.three.pay.paymentapi.except.BusinessException;
import com.three.pay.paymentapi.result.PayResult;
import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentchannel.param.ChannelRespParam;
import com.three.pay.paymentcommon.dto.MerChannelInfo;
import com.three.pay.paymentcommon.po.MerOrderPo;
import com.three.pay.paymentcommon.po.MerOrderQueryPo;
import com.three.pay.paymentjdbc.entity.PayOrderDetail;
import com.three.pay.paymentservice.service.channel.IChannelService;
import com.three.pay.paymentservice.service.core.IChannelRouteCenter;
import com.three.pay.paymentservice.service.core.IOrderCenter;
import com.three.pay.paymentservice.service.core.IPayReqLog;
import com.three.pay.paymentservice.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:luiz
 * @Date: 2018/5/25 16:38
 * @Descripton: 创建订单统一处理
 * @Modify :
 **/
@Service
public class MerOrderImpl implements ITradeProcess {
    private static final Logger logger= LoggerFactory.getLogger(MerOrderImpl.class);
    @Autowired
    IChannelRouteCenter iChannelRouteCenter;
    @Autowired
    IChannelService iChannelService;
    @Autowired
    IOrderCenter iOrderCenter;
    @Autowired
    IPayReqLog iPayReqLog;


    @Override
    public boolean isSupport(ServiceNameEnum serviceNameEnum) {
        return ServiceNameEnum.UNION_CREATE_ORDER.equals(serviceNameEnum);
    }

    @Override
    public PayResult execute(CommonReqParam commonReqVo) {
        PayResult payResult=PayResult.newSuccess();
        try{
            //1.参数校验
            String reqContent=commonReqVo.getReqContent();
            MerOrderPo merOrderPo= JSON.parseObject(reqContent, MerOrderPo.class);
            ValidatorUtil.validateEntity(merOrderPo);
            MerOrderQueryPo merOrderQueryPo=new MerOrderQueryPo();
            merOrderQueryPo.setOrderList(merOrderPo.getOrderList());
            //数据校验是否已存在
            PayOrderDetail oldPayOrderDetail= iOrderCenter.queryOrder(merOrderQueryPo);
            if(oldPayOrderDetail!=null){
                throw new BusinessException(ResultCode.COMMON_DATA_EXISTS);
            }
            //2.选择渠道路由
            MerChannelInfo merChannelInfo =iChannelRouteCenter.switchChannelRoute(commonReqVo.getMerNo(),merOrderPo.getProductNo());
            //3.记录数据，生成订单号
            iOrderCenter.createOrder(merChannelInfo,commonReqVo,merOrderPo);
            //4.执行渠道层动作
            ChannelRespParam channelRespParam=iChannelService.channelProcess(merChannelInfo,commonReqVo);
            //执行同步请求存储动作
            iPayReqLog.save(channelRespParam, merChannelInfo);
            //5.返回结果(创建订单后,等待付款)
            payResult.setData(channelRespParam.getRespContent());
        }catch (BusinessException be){
            logger.error("【订单统一处理】请求参数:{},发生业务异常:{}",commonReqVo,be);
            payResult.setError(be.getCode(),be.getMsg());
        } catch (Exception e){
            logger.error("【订单统一处理】请求参数:{},发生异常:{}",commonReqVo,e);
            payResult.setError(ResultCode.FAIL.getCode(),e.getMessage());
        }
        return payResult;
    }


}
