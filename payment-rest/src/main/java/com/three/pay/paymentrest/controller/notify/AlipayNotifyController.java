package com.three.pay.paymentrest.controller.notify;

import com.three.pay.paymentcommon.enums.PayStatusEnum;
import com.three.pay.paymentcommon.po.notify.NotifyPayParamPo;
import com.three.pay.paymentcommon.utils.PairString;
import com.three.pay.paymentcommon.utils.RSAUtils;
import com.three.pay.paymentjdbc.entity.ChannelDetail;
import com.three.pay.paymentrest.utils.RequestUtils;
import com.three.pay.paymentservice.service.core.IChannelDetail;
import com.three.pay.paymentservice.service.core.IOrderCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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
        String signParam=PairString.createLinkStringForEncode(notifyParamsMap);
        ChannelDetail channelDetail=iChannelDetail.findByPayWay("ALIPAY_SCAN_CODE");
        boolean verifyFlag=RSAUtils.verifyWithRsa2(signParam,signValue,channelDetail.getPubKey());
        if(!verifyFlag){
            logger.info("【支付宝网页支付】验证签名失败:{}",notifyParamsMap);
            return "fail";
        }
        String tradeStatus=notifyParamsMap.get("trade_status");
        NotifyPayParamPo notifyPayParamPo=new NotifyPayParamPo();
        notifyPayParamPo.setThirdTradeNo(notifyParamsMap.get("trade_no"));
        notifyPayParamPo.setThirdTradeStatus(tradeStatus);
        notifyPayParamPo.setPaySeqNo(notifyParamsMap.get("out_trade_no"));
        if("WAIT_BUYER_PAY".equals(tradeStatus)){
            notifyPayParamPo.setTradeStatus(PayStatusEnum.PAY_INIT.getCode());
        }else  if("TRADE_CLOSED".equals(tradeStatus)){
            notifyPayParamPo.setTradeStatus(PayStatusEnum.PAY_CLOSE.getCode());
        }else  if("TRADE_SUCCESS".equals(tradeStatus)){
            notifyPayParamPo.setTradeStatus(PayStatusEnum.PAY_SUCCESS.getCode());
        }
        iOrderCenter.notifyOrder(notifyPayParamPo);
        return "success";
    }
}
