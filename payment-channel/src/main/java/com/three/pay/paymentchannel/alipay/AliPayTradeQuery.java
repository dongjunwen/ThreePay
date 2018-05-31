package com.three.pay.paymentchannel.alipay;

import com.alibaba.fastjson.JSONObject;
import com.three.pay.paymentchannel.IChannelCore;
import com.three.pay.paymentchannel.param.ChannelReqParam;
import com.three.pay.paymentchannel.param.ChannelRespParam;
import com.three.pay.paymentcommon.dto.MerChannelInfo;
import com.three.pay.paymentcommon.enums.ChannelActionEnum;
import com.three.pay.paymentcommon.enums.ChannelCodeEnum;
import com.three.pay.paymentcommon.enums.PayStatusEnum;
import com.three.pay.paymentcommon.enums.PayWayEnum;
import com.three.pay.paymentcommon.utils.DateUtil;
import com.three.pay.paymentcommon.utils.HttpClientUtil;
import com.three.pay.paymentcommon.utils.PairString;
import com.three.pay.paymentcommon.utils.RSAUtils;
import com.three.pay.paymentjdbc.entity.PayOrderDetail;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:luiz
 * @Date: 2018/5/28 11:16
 * @Descripton:支付宝查询接口
 * @Modify :
 **/
@Service
public class AliPayTradeQuery implements IChannelCore {
    private static final Logger logger= LoggerFactory.getLogger(AliPayTradeQuery.class);

    private static final String alipayUrl="https://openapi.alipaydev.com/gateway.do";


    @Override
    public boolean isSupport(PayWayEnum payWayEnum, ChannelActionEnum channelActionEnum) {
        boolean flag=ChannelActionEnum.ORDER_QEURY.equals(channelActionEnum) && PayWayEnum.ALIPAY_SCAN_CODE.equals(payWayEnum);
        return flag;
    }

    @Override
    public ChannelRespParam channelProcess(MerChannelInfo merChannelInfo, ChannelReqParam channelReqParam) {
        //1.构造请求参数
        Map reqParam=buildReqParam(merChannelInfo,channelReqParam);
        logger.info("[支付宝查询]请求内容:{}",reqParam.toString());
        //2.发送请求
        String respContent= HttpClientUtil.doPost(alipayUrl,reqParam);
        logger.info("[支付宝查询]返回内容:{}",respContent);
        ChannelRespParam channelRespParam=convertRespData(respContent,merChannelInfo);
        //3.处理响应
        return channelRespParam;
    }

    private ChannelRespParam convertRespData(String respContent,MerChannelInfo merChannelInfo) {
        ChannelRespParam channelRespParam=new ChannelRespParam();
        JSONObject respJson=JSONObject.parseObject(respContent);
        String alipayTradeResp=respJson.getString("alipay_trade_query_response");
        JSONObject alipayTradeRespJson=JSONObject.parseObject(alipayTradeResp);
        String code=alipayTradeRespJson.getString("code");
        String msg=alipayTradeRespJson.getString("msg");

        String signValue=respJson.getString("sign");
        int firstIndex=respContent.indexOf(":{")+1;
        int endIndex=respContent.indexOf("},")+1;
        String signParam=respContent.substring(firstIndex,endIndex);
        logger.debug("【支付宝查询】待验证签名原字符串:{}",signParam);
        boolean verifyFlag=RSAUtils.verifyWithRsa2(signParam,signValue,merChannelInfo.getPubKey());
        if(!verifyFlag){
            logger.info("【支付宝查询】验证签名失败:{}",respJson);
            channelRespParam.setRespCode(ChannelCodeEnum.SIGN_VALID_FAIL.getCode());
            channelRespParam.setRespMsg(ChannelCodeEnum.SIGN_VALID_FAIL.getName());
            return channelRespParam;
        }
        PayOrderDetail payOrderDetail=new PayOrderDetail();
        //状态码转换
        if(!AlipayConstant.ALIPAY_RESP_SUCCESS.equals(code)){
            channelRespParam.setRespCode(ChannelCodeEnum.FAIL.getCode());
            String subCode=alipayTradeRespJson.getString("sub_code");
            String subMsg=alipayTradeRespJson.getString("sub_msg");
            channelRespParam.setRespMsg(subMsg);
            if(AlipayConstant.TRADE_NOT_EXIST.equals(subCode)){
                channelRespParam.setRespCode(ChannelCodeEnum.TRADE_NOT_EXIST.getCode());
                //补偿订单逻辑 查询不到就关闭
                payOrderDetail.setPayStatus(PayStatusEnum.PAY_CLOSE.getCode());
            }
            payOrderDetail.setPaySeqNo(merChannelInfo.getInnerSeqNo());
            channelRespParam.setRespContent(JSONObject.toJSONString(payOrderDetail));
            return channelRespParam;
        }

        channelRespParam.setRespCode(ChannelCodeEnum.SUCCESS.getCode());
        channelRespParam.setRespMsg("成功");

        String tradeStatus=alipayTradeRespJson.getString("trade_status");
        //状态码转换
        if("WAIT_BUYER_PAY".equals(tradeStatus)){
            payOrderDetail.setPayStatus(PayStatusEnum.PAY_INIT.getCode());
        }else  if("TRADE_CLOSED".equals(tradeStatus)){
            payOrderDetail.setPayStatus(PayStatusEnum.PAY_CLOSE.getCode());
        }else  if("TRADE_SUCCESS".equals(tradeStatus)){
            payOrderDetail.setPayStatus(PayStatusEnum.PAY_SUCCESS.getCode());
        }else if("TRADE_FINISHED".equals(tradeStatus)){
            payOrderDetail.setPayStatus(PayStatusEnum.PAY_FINISH.getCode());
        }
        payOrderDetail.setRespPayNo(alipayTradeRespJson.getString("trade_no"));
        payOrderDetail.setPaySeqNo(alipayTradeRespJson.getString("out_trade_no"));
        if(alipayTradeRespJson.containsKey("gmt_payment") && StringUtils.isNotEmpty(alipayTradeRespJson.getString("gmt_payment"))){
            Date gmtPayment = DateUtil.formatDate(alipayTradeRespJson.getString("gmt_payment"),"yyyy-MM-dd HH:mm:ss");
            payOrderDetail.setPaySuccessTime(new Timestamp(gmtPayment.getTime()));
        }
        payOrderDetail.setSellerAcct(alipayTradeRespJson.getString("seller_id"));
        payOrderDetail.setPaylerAcct(alipayTradeRespJson.getString("buyer_id"));
        String thirdDiscountAmt=alipayTradeRespJson.getString("point_amount");
        if(StringUtils.isNotEmpty(thirdDiscountAmt))
        payOrderDetail.setThirdDiscountAmt(new BigDecimal(thirdDiscountAmt));
        String buyerPayAmount=alipayTradeRespJson.getString("buyer_pay_amount");
        if(StringUtils.isNotEmpty(buyerPayAmount))
        payOrderDetail.setBuyPayAmt(new BigDecimal(buyerPayAmount));
        String invoiceAmount=alipayTradeRespJson.getString("invoice_amount");
        if(StringUtils.isNotEmpty(invoiceAmount))
        payOrderDetail.setBuyInvoiceAmt(new BigDecimal(invoiceAmount));
        String merRecvAmt=alipayTradeRespJson.getString("receipt_amount");
        if(StringUtils.isNotEmpty(merRecvAmt))
        payOrderDetail.setMerRecvAmt(new BigDecimal(merRecvAmt));

        channelRespParam.setRespContent(JSONObject.toJSONString(payOrderDetail));
        return channelRespParam;
    }

    private Map buildReqParam(MerChannelInfo merChannelInfo, ChannelReqParam channelReqParam) {
        Map reqParam=new ConcurrentHashMap<>();
        reqParam.put("app_id", merChannelInfo.getChannelPartenerNo());
        reqParam.put("method","alipay.trade.query");
        reqParam.put("format","json");
        reqParam.put("charset","utf-8");
        reqParam.put("timestamp",DateUtil.getDateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"));
        reqParam.put("version","1.0");
        reqParam.put("sign_type", merChannelInfo.getSignType());

        JSONObject bizContent=new JSONObject();
        bizContent.put("out_trade_no", merChannelInfo.getInnerSeqNo());
        bizContent.put("trade_no",merChannelInfo.getThirdSeqNo());
        reqParam.put("biz_content",bizContent.toString());

        String content=  PairString.createLinkString(reqParam);
        String signValue=RSAUtils.signWithRsa2(content, merChannelInfo.getPrivKey());
        reqParam.put("sign",signValue);
        return reqParam;
    }






}
