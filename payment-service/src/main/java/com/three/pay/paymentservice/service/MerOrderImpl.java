package com.three.pay.paymentservice.service;

import com.alibaba.fastjson.JSON;
import com.three.pay.paymentapi.api.ITradeProcess;
import com.three.pay.paymentapi.enums.ResultCode;
import com.three.pay.paymentapi.enums.ServiceNameEnum;
import com.three.pay.paymentapi.except.BusinessException;
import com.three.pay.paymentapi.result.PayResult;
import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentchannel.param.ChannelRespParam;
import com.three.pay.paymentcommon.dto.MerOrderDto;
import com.three.pay.paymentcommon.enums.StatusEnum;
import com.three.pay.paymentcommon.po.MerOrderPo;
import com.three.pay.paymentjdbc.entity.*;
import com.three.pay.paymentservice.service.channel.IChannelService;
import com.three.pay.paymentservice.service.core.*;
import com.three.pay.paymentservice.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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
    IProductInfo iProductInfo;
    @Autowired
    IMerInfo iMerInfo;
    @Autowired
    IProductChannelRoute iProductChannelRoute;
    @Autowired
    IChannelInfo iChannelInfo;
    @Autowired
    IChannelDetail ichannelDetail;
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
            //2.选择渠道路由
            MerOrderDto merOrderDto=switchChannelRoute(commonReqVo.getMerNo(),commonReqVo.getProductNo());
            //3.记录数据，生成订单号
            iOrderCenter.createOrder(merOrderDto,commonReqVo,merOrderPo);
            //4.执行渠道层动作
            ChannelRespParam channelRespParam=iChannelService.channelProcess(merOrderDto,commonReqVo);
            //执行同步请求存储动作
            iPayReqLog.save(channelRespParam,merOrderDto);
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

    /**
     * 根据商户号、产品编码选择渠道对应的渠道
     * @param merNo
     * @param productNo
     */
    private MerOrderDto switchChannelRoute(String merNo, String productNo) {
        MerOrderDto merOrderDto=new MerOrderDto();
        //1.校验商户是否有效
        MerInfo merInfo=iMerInfo.findByMerNo(merNo);
        if(merInfo==null){
            throw new BusinessException(ResultCode.MER_NOT_VALID);
        }
        if(!StatusEnum.YES.getCode().equals(merInfo.getStatus())){
           throw new BusinessException(ResultCode.MER_NOT_VALID);
        }
        merOrderDto.setMerNo(merNo);
        merOrderDto.setMerName(merInfo.getMerName());
        //校验产品是否有效
        ProductInfo productInfo=iProductInfo.findByProductNo(productNo);
        if(productInfo==null){
            throw new BusinessException(ResultCode.PRODUCT_NOT_EXISTS);
        }
        if(!StatusEnum.YES.getCode().equals(productInfo.getStatus())){
            throw new BusinessException(ResultCode.PRODUCT_NOT_VALID);
        }
        merOrderDto.setProductNo(productNo);
        merOrderDto.setProductName(productInfo.getProductName());


        //校验产品对应的渠道是否有效
        List<ProductChannelRoute> productChannelRouteList= iProductChannelRoute.findAvailable();
        if(productChannelRouteList==null || productChannelRouteList.size()<=0){
            throw new BusinessException(ResultCode.PRODUCT_CHANNEL_NOT_EXISTS);
        }
        //按照优先级获取最高的 TODO 此处算法可以调整 或随机或指定或按照规则
        Collections.sort(productChannelRouteList,(p1,p2)->String.valueOf(p1.getUseLevel()).compareTo(String.valueOf(p2.getUseLevel())));
        ProductChannelRoute productChannelRoute=productChannelRouteList.get(0);
        merOrderDto.setChannelPartenerNo(productChannelRoute.getChannelPartenerNo());
        merOrderDto.setChannelPartenerName(productChannelRoute.getChannelPartenrName());
        merOrderDto.setMerFee(productChannelRoute.getMerFee());

        ChannelDetail channelDetail=ichannelDetail.findByPayWay(productChannelRoute.getPayWay());
        if(channelDetail==null){
            throw new BusinessException(ResultCode.CHANNELDETAIL_NOT_EXISTS);
        }
        if(!StatusEnum.YES.getCode().equals(channelDetail.getStatus())){
            throw new BusinessException(ResultCode.CHANNELDETAIL_NOT_VALID);
        }
        merOrderDto.setPayWay(channelDetail.getPayWay());
        merOrderDto.setPayWayName(channelDetail.getPayWayName());
        merOrderDto.setChannelFee(channelDetail.getChannelFee());
        merOrderDto.setSignType(channelDetail.getSignType());
        merOrderDto.setPrivKey(channelDetail.getPrivKey());
        merOrderDto.setPubKey(channelDetail.getPubKey());
        merOrderDto.setResvKey(channelDetail.getResvKey());
        merOrderDto.setResv1(channelDetail.getResv1());
        merOrderDto.setResv2(channelDetail.getResv2());
        //校验商户渠道是否有效
        ChannelInfo channelInfo=iChannelInfo.findByChannelCode(channelDetail.getChannelCode());
        if(channelInfo==null){
            throw new BusinessException(ResultCode.CHANNEL_NOT_EXISTS);
        }
        if(!StatusEnum.YES.getCode().equals(channelInfo.getStatus())){
            throw new BusinessException(ResultCode.CHANNEL_NOT_VALID);
        }
        merOrderDto.setChannelCode(channelInfo.getChannelCode());
        merOrderDto.setChannelName(channelInfo.getChannelName());

        //4.返回渠道详情
        return merOrderDto;
    }
}
