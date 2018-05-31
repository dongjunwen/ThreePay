package com.three.pay.paymentrest.controller.notify;

import com.three.pay.paymentcommon.enums.PayStatusEnum;
import com.three.pay.paymentcommon.enums.TradeTypeEnum;
import com.three.pay.paymentcommon.po.notify.NotifyPayParamPo;
import com.three.pay.paymentcommon.utils.DateUtil;
import com.three.pay.paymentcommon.utils.PairString;
import com.three.pay.paymentcommon.utils.RSAUtils;
import com.three.pay.paymentjdbc.entity.ChannelDetail;
import com.three.pay.paymentjdbc.entity.PayNotifyLog;
import com.three.pay.paymentrest.utils.RequestUtils;
import com.three.pay.paymentservice.service.core.IChannelDetail;
import com.three.pay.paymentservice.service.core.IOrderCenter;
import com.three.pay.paymentservice.service.core.IPayNotifyLog;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

/**
 * @Author:luiz
 * @Date: 2018/5/30 14:06
 * @Descripton:支付宝通知回调
 * @Modify :
 * WAIT_BUYER_PAY	交易创建，等待买家付款
TRADE_CLOSED	未付款交易超时关闭，或支付完成后全额退款
TRADE_SUCCESS	交易支付成功
TRADE_FINISHED	交易结束，不可退款
 **/
@Controller
@RequestMapping("/AlipayNotify")
public class AlipayNotifyController {

    private static final Logger logger= LoggerFactory.getLogger(AlipayNotifyController.class);

    @Autowired
    IChannelDetail iChannelDetail;
    @Autowired
    IOrderCenter iOrderCenter;
    @Autowired
    IPayNotifyLog iPayNotifyLog;

    @RequestMapping("pagePay")
    public String payNotify(HttpServletRequest request){
        Map<String,String> notifyParamsMap=RequestUtils.getAlipayNotify(request);
        if(notifyParamsMap==null || !notifyParamsMap.containsKey("sign")){
            logger.error("请求参数错误...");
            return "fail";
        }

        logger.info("【支付宝网页支付】获取通知参数:{}",notifyParamsMap);
        String signValue=notifyParamsMap.get("sign");
        notifyParamsMap.remove("sign");
        notifyParamsMap.remove("sign_type");
        String signParam=PairString.createLinkString(notifyParamsMap);
        ChannelDetail channelDetail=iChannelDetail.findByPayWay("ALIPAY_SCAN_CODE");
        boolean verifyFlag=RSAUtils.verifyWithRsa2(signParam,signValue,channelDetail.getPubKey());
        if(!verifyFlag){
            logger.info("【支付宝网页支付】验证签名失败:{}",notifyParamsMap);
            return "fail";
        }
        saveLog(notifyParamsMap);
        String tradeStatus=notifyParamsMap.get("trade_status");
        NotifyPayParamPo notifyPayParamPo=new NotifyPayParamPo();
        notifyPayParamPo.setThirdTradeNo(notifyParamsMap.get("trade_no"));
        Date gmtCreate = DateUtil.formatDate(notifyParamsMap.get("gmt_create"),"yyyy-MM-dd HH:mm:ss");
        notifyPayParamPo.setThirdCreateTime(gmtCreate);
        Date gmtPayment = DateUtil.formatDate(notifyParamsMap.get("gmt_payment"),"yyyy-MM-dd HH:mm:ss");
        notifyPayParamPo.setThirdPayTime(gmtPayment);
        notifyPayParamPo.setSellerId(notifyParamsMap.get("seller_id"));
        notifyPayParamPo.setBuyllerId(notifyParamsMap.get("buyer_id"));
        notifyPayParamPo.setThirdTradeStatus(tradeStatus);
        notifyPayParamPo.setPaySeqNo(notifyParamsMap.get("out_trade_no"));
        notifyPayParamPo.setThirdNotifyContent(notifyParamsMap.toString());
        String thirdDiscountAmt=notifyParamsMap.get("point_amount");
        if(StringUtils.isNotEmpty(thirdDiscountAmt))
            notifyPayParamPo.setThirdDiscountAmt(new BigDecimal(thirdDiscountAmt));
        String buyerPayAmount=notifyParamsMap.get("buyer_pay_amount");
        if(StringUtils.isNotEmpty(buyerPayAmount))
            notifyPayParamPo.setBuyPyAmt(new BigDecimal(buyerPayAmount));
        String invoiceAmount=notifyParamsMap.get("invoice_amount");
        if(StringUtils.isNotEmpty(invoiceAmount))
            notifyPayParamPo.setBuyInvoiceAmt(new BigDecimal(invoiceAmount));
        String merRecvAmt=notifyParamsMap.get("receipt_amount");
        if(StringUtils.isNotEmpty(merRecvAmt))
            notifyPayParamPo.setMerRecvAmt(new BigDecimal(merRecvAmt));
        if("WAIT_BUYER_PAY".equals(tradeStatus)){
            notifyPayParamPo.setTradeStatus(PayStatusEnum.PAY_INIT.getCode());
        }else  if("TRADE_CLOSED".equals(tradeStatus)){
            notifyPayParamPo.setTradeStatus(PayStatusEnum.PAY_CLOSE.getCode());
        }else  if("TRADE_SUCCESS".equals(tradeStatus)){
            notifyPayParamPo.setTradeStatus(PayStatusEnum.PAY_SUCCESS.getCode());
        }else if("TRADE_FINISHED".equals(tradeStatus)){
            notifyPayParamPo.setTradeStatus(PayStatusEnum.PAY_FINISH.getCode());
        }
        iOrderCenter.notifyOrder(notifyPayParamPo);
        return "success";
    }

    private void saveLog(Map<String, String> notifyParamsMap) {
        String notifyId=notifyParamsMap.get("notify_id");
        PayNotifyLog payNotifyLog=new PayNotifyLog();
        payNotifyLog.setThirdNotifyId(notifyId);
        payNotifyLog.setPayWay("ALIPAY_SCAN_CODE");
        payNotifyLog.setPayWayName("支付宝扫码支付");
        payNotifyLog.setTradeNo(notifyParamsMap.get("out_trade_no"));
        payNotifyLog.setTradeType(TradeTypeEnum.PAY.getCode());
        payNotifyLog.setNotifyContent(notifyParamsMap.toString());
        Date nowDate=new Date();
        payNotifyLog.setCreateTime(new Timestamp(nowDate.getTime()));
        iPayNotifyLog.saveNotifyLog(payNotifyLog);
    }
}
