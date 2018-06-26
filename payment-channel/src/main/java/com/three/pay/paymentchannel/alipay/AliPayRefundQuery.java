package com.three.pay.paymentchannel.alipay;

import com.alibaba.fastjson.JSONObject;
import com.three.pay.paymentchannel.IChannelCore;
import com.three.pay.paymentchannel.config.ChannelConstant;
import com.three.pay.paymentchannel.param.ChannelReqParam;
import com.three.pay.paymentchannel.param.ChannelRespParam;
import com.three.pay.paymentcommon.dto.MerChannelInfo;
import com.three.pay.paymentcommon.enums.ChannelActionEnum;
import com.three.pay.paymentcommon.enums.ChannelCodeEnum;
import com.three.pay.paymentcommon.enums.PayWayEnum;
import com.three.pay.paymentcommon.enums.RefundStatusEnum;
import com.three.pay.paymentcommon.utils.HttpClientUtil;
import com.three.pay.paymentcommon.utils.PairString;
import com.three.pay.paymentcommon.utils.RSAUtils;
import com.three.pay.paymentjdbc.entity.PayRefundDetail;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:luiz
 * @Date: 2018/5/28 11:16
 * @Descripton:支付宝退款查询接口
 * @Modify :
 **/
@Service
public class AliPayRefundQuery implements IChannelCore {
    private static final Logger logger= LoggerFactory.getLogger(AliPayRefundQuery.class);
    @Autowired
    ChannelConstant channelConstant;

    @Override
    public boolean isSupport(PayWayEnum payWayEnum, ChannelActionEnum channelActionEnum) {
        boolean flag=ChannelActionEnum.REFUND_QUERY.equals(channelActionEnum) && PayWayEnum.ALIPAY_SCAN_CODE.equals(payWayEnum);
        return flag;
    }

    @Override
    public ChannelRespParam channelProcess(MerChannelInfo merChannelInfo, ChannelReqParam channelReqParam) {
        //1.构造请求参数
        Map reqParam=buildReqParam(merChannelInfo,channelReqParam);
        logger.info("[支付宝退款查询]返回内容:{}",reqParam);
        //2.发送请求
        String respContent= HttpClientUtil.doPost(channelConstant.getAlipayUrl(),reqParam);
        logger.info("[支付宝退款查询]返回内容:{}",respContent);
        //3.处理响应
        ChannelRespParam channelRespParam=convertRespData(respContent,merChannelInfo);
        //3.处理响应
        return channelRespParam;
    }
    private ChannelRespParam convertRespData(String respContent, MerChannelInfo merChannelInfo) {
        ChannelRespParam channelRespParam=new ChannelRespParam();
        JSONObject respJson=JSONObject.parseObject(respContent);
        String alipayRefundResp=respJson.getString("alipay_trade_fastpay_refund_query_response");
        JSONObject alipayTradeRespJson=JSONObject.parseObject(alipayRefundResp);
        String code=alipayTradeRespJson.getString("code");
        String msg=alipayTradeRespJson.getString("msg");
        String signValue=respJson.getString("sign");
        int firstIndex=respContent.indexOf(":{")+1;
        int endIndex=respContent.indexOf("},")+1;
        String signParam=respContent.substring(firstIndex,endIndex);
        logger.debug("【支付宝退款查询】待验证签名原字符串:{}",signParam);
        boolean verifyFlag=RSAUtils.verifyWithRsa2(signParam,signValue,merChannelInfo.getPubKey());
        if(!verifyFlag){
            logger.info("【支付宝退款查询】验证签名失败:{}",respJson);
            channelRespParam.setRespCode(ChannelCodeEnum.SIGN_VALID_FAIL.getCode());
            channelRespParam.setRespMsg(ChannelCodeEnum.SIGN_VALID_FAIL.getName());
            return channelRespParam;
        }
        PayRefundDetail payRefundDetail=new PayRefundDetail();
        //状态码转换
        if(!AlipayConstant.ALIPAY_RESP_SUCCESS.equals(code)){
            channelRespParam.setRespCode(ChannelCodeEnum.FAIL.getCode());
            String subCode=alipayTradeRespJson.getString("sub_code");
            String subMsg=alipayTradeRespJson.getString("sub_msg");
            channelRespParam.setRespMsg(subMsg);
            payRefundDetail.setRefundStatus(RefundStatusEnum.REFUND_FAIL.getCode());
            payRefundDetail.setPaySeqNo(merChannelInfo.getInnerSeqNo());
            channelRespParam.setRespContent(JSONObject.toJSONString(payRefundDetail));
            return channelRespParam;
        }
        channelRespParam.setRespCode(ChannelCodeEnum.SUCCESS.getCode());
        channelRespParam.setRespMsg("成功");
        //状态码转换
        payRefundDetail.setRefundStatus(RefundStatusEnum.REFUND_SUCCESS.getCode());
        payRefundDetail.setRefundSeqNo(merChannelInfo.getInnerRefundSeqNo());
        payRefundDetail.setRespRefundNo(alipayTradeRespJson.getString("trade_no"));
        payRefundDetail.setPaySeqNo(alipayTradeRespJson.getString("out_trade_no"));
        String thirdDiscountAmt=alipayTradeRespJson.getString("present_refund_discount_amount");
        if(StringUtils.isNotEmpty(thirdDiscountAmt))
            payRefundDetail.setThirdRefundDiscountAmt(new BigDecimal(thirdDiscountAmt));
        String buyerPayAmount=alipayTradeRespJson.getString("present_refund_buyer_amount");
        if(StringUtils.isNotEmpty(buyerPayAmount))
            payRefundDetail.setBuyRefundAmt(new BigDecimal(buyerPayAmount));
        String merRecvAmt=alipayTradeRespJson.getString("present_refund_mdiscount_amount");
        if(StringUtils.isNotEmpty(merRecvAmt))
            payRefundDetail.setMerRefundDiscountAmt(new BigDecimal(merRecvAmt));
        channelRespParam.setRespContent(JSONObject.toJSONString(payRefundDetail));
        return channelRespParam;
    }

    private Map buildReqParam(MerChannelInfo merChannelInfo, ChannelReqParam channelReqParam) {
        Map reqParam=new ConcurrentHashMap<>();
        reqParam.put("app_id", merChannelInfo.getChannelPartenerNo());
        reqParam.put("method","alipay.trade.fastpay.refund.query");
        reqParam.put("format","json");
        reqParam.put("charset","utf-8");
        reqParam.put("timestamp",channelReqParam.getRequestTime());
        reqParam.put("version","1.0");
        reqParam.put("sign_type", merChannelInfo.getSignType());
        JSONObject bizContent=new JSONObject();
        bizContent.put("out_trade_no", merChannelInfo.getInnerSeqNo());
        bizContent.put("trade_no", merChannelInfo.getThirdSeqNo());
        bizContent.put("out_request_no", merChannelInfo.getInnerRefundSeqNo());
        reqParam.put("biz_content",bizContent.toString());
        String content=  PairString.createLinkString(reqParam);
        String signValue=RSAUtils.signWithRsa2(content, merChannelInfo.getPrivKey());
        reqParam.put("sign",signValue);
        return reqParam;
    }






}
