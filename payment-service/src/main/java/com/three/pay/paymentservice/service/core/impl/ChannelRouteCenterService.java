package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentapi.enums.ResultCode;
import com.three.pay.paymentapi.except.BusinessException;
import com.three.pay.paymentcommon.dto.MerChannelInfo;
import com.three.pay.paymentcommon.enums.ChannelActionEnum;
import com.three.pay.paymentcommon.enums.StatusEnum;
import com.three.pay.paymentjdbc.entity.*;
import com.three.pay.paymentservice.service.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @Author:luiz
 * @Date: 2018/5/31 13:53
 * @Descripton:
 * @Modify :
 **/
@Service
public class ChannelRouteCenterService implements IChannelRouteCenter {
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

    /**
     * 根据商户号、产品编码选择渠道对应的渠道
     * @param merNo
     * @param productNo
     */
    public MerChannelInfo switchChannelRoute(String merNo, String productNo) {
        MerChannelInfo merChannelInfo =new MerChannelInfo();
        merChannelInfo.setChannelAction(ChannelActionEnum.CREATE_ORDER.getCode());
        //1.校验商户是否有效
        MerInfo merInfo=iMerInfo.findByMerNo(merNo);
        if(merInfo==null){
            throw new BusinessException(ResultCode.MER_NOT_VALID);
        }
        if(!StatusEnum.YES.getCode().equals(merInfo.getStatus())){
            throw new BusinessException(ResultCode.MER_NOT_VALID);
        }
        merChannelInfo.setMerNo(merNo);
        merChannelInfo.setMerName(merInfo.getMerName());
        //校验产品是否有效
        ProductInfo productInfo=iProductInfo.findByProductNo(productNo);
        if(productInfo==null){
            throw new BusinessException(ResultCode.PRODUCT_NOT_EXISTS);
        }
        if(!StatusEnum.YES.getCode().equals(productInfo.getStatus())){
            throw new BusinessException(ResultCode.PRODUCT_NOT_VALID);
        }
        merChannelInfo.setProductNo(productNo);
        merChannelInfo.setProductName(productInfo.getProductName());


        //校验产品对应的渠道是否有效
        List<ProductChannelRoute> productChannelRouteList= iProductChannelRoute.findByMerNoAndProductNo(merNo,productNo);
        if(productChannelRouteList==null || productChannelRouteList.size()<=0){
            throw new BusinessException(ResultCode.PRODUCT_CHANNEL_NOT_EXISTS);
        }
        //按照优先级获取最高的 TODO 此处算法可以调整 或随机或指定或按照规则
        Collections.sort(productChannelRouteList,(p1, p2)->String.valueOf(p1.getUseLevel()).compareTo(String.valueOf(p2.getUseLevel())));
        ProductChannelRoute productChannelRoute=productChannelRouteList.get(0);
        merChannelInfo.setChannelPartenerNo(productChannelRoute.getChannelPartenerNo());
        merChannelInfo.setChannelPartenerName(productChannelRoute.getChannelPartenrName());
        merChannelInfo.setMerFee(productChannelRoute.getMerFee());

        ChannelDetail channelDetail=ichannelDetail.findByPayWay(productChannelRoute.getPayWay());
        if(channelDetail==null){
            throw new BusinessException(ResultCode.CHANNELDETAIL_NOT_EXISTS);
        }
        if(!StatusEnum.YES.getCode().equals(channelDetail.getStatus())){
            throw new BusinessException(ResultCode.CHANNELDETAIL_NOT_VALID);
        }
        merChannelInfo.setPayWay(channelDetail.getPayWay());
        merChannelInfo.setPayWayName(channelDetail.getPayWayName());
        merChannelInfo.setChannelFee(channelDetail.getChannelFee());
        merChannelInfo.setSignType(channelDetail.getSignType());
        merChannelInfo.setPrivKey(channelDetail.getPrivKey());
        merChannelInfo.setPubKey(channelDetail.getPubKey());
        merChannelInfo.setResvKey(channelDetail.getResvKey());
        merChannelInfo.setResv1(channelDetail.getResv1());
        merChannelInfo.setResv2(channelDetail.getResv2());

        //校验商户渠道是否有效
        ChannelInfo channelInfo=iChannelInfo.findByChannelCode(channelDetail.getChannelCode());
        if(channelInfo==null){
            throw new BusinessException(ResultCode.CHANNEL_NOT_EXISTS);
        }
        if(!StatusEnum.YES.getCode().equals(channelInfo.getStatus())){
            throw new BusinessException(ResultCode.CHANNEL_NOT_VALID);
        }
        merChannelInfo.setChannelCode(channelInfo.getChannelCode());
        merChannelInfo.setChannelName(channelInfo.getChannelName());

        //4.返回渠道详情
        return merChannelInfo;
    }

    @Override
    public MerChannelInfo buildMerChannelInfo(PayOrderDetail payOrderDetail, ChannelActionEnum channelActionEnum) {
        MerChannelInfo merChannelInfo=new MerChannelInfo();
        merChannelInfo.setChannelAction(channelActionEnum.getCode());
        merChannelInfo.setPayWay(payOrderDetail.getPayWay());
        ProductChannelRoute productChannelRoute=iProductChannelRoute.findByMerNoAndPayWay(payOrderDetail.getMerNo(),payOrderDetail.getPayWay());
        merChannelInfo.setChannelPartenerNo(productChannelRoute.getChannelPartenerNo());
        merChannelInfo.setChannelPartenerName(productChannelRoute.getChannelPartenrName());
        merChannelInfo.setMerFee(productChannelRoute.getMerFee());

        ChannelDetail channelDetail=ichannelDetail.findByPayWay(payOrderDetail.getPayWay());
        merChannelInfo.setPayWayName(channelDetail.getPayWayName());
        merChannelInfo.setChannelFee(channelDetail.getChannelFee());
        merChannelInfo.setSignType(channelDetail.getSignType());
        merChannelInfo.setPrivKey(channelDetail.getPrivKey());
        merChannelInfo.setPubKey(channelDetail.getPubKey());
        merChannelInfo.setResvKey(channelDetail.getResvKey());
        merChannelInfo.setResv1(channelDetail.getResv1());
        merChannelInfo.setResv2(channelDetail.getResv2());
        //设置交易流水
        merChannelInfo.setThirdSeqNo(payOrderDetail.getRespPayNo());
        merChannelInfo.setInnerTradeNo(payOrderDetail.getTradeNo());
        merChannelInfo.setInnerSeqNo(payOrderDetail.getPaySeqNo());
        return merChannelInfo;
    }


}
