package com.three.pay.paymentchannel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author:luiz
 * @Date: 2018/6/1 15:02
 * @Descripton:
 * @Modify :
 **/
@Component
public class ChannelConstant {
    //异步通知地址
    @Value("${three.pay.notifyUrl}")
    private  String notifyUrl;
    //页面跳转地址
    @Value("${three.pay.forwardUrl}")
    private  String forwardUrl;
    //支付宝网关请求地址
    @Value("${gateway.alipay.url}")
    private   String alipayUrl;

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public String getForwardUrl() {
        return forwardUrl;
    }

    public String getAlipayUrl() {
        return alipayUrl;
    }
}
