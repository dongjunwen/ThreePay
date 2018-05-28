package com.three.pay.paymentchannel.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author:luiz
 * @Date: 2018/5/28 11:21
 * @Descripton:渠道层统一请求参数入口
 * @Modify :
 **/
@Getter
@Setter
@ToString
public class ChannelReqParam {
    private String channelReqNo;
    private String merNo;
    private String productNo;
    private String serviceName;
    private String charsetCode;
    private String signType;
    private String signVlaue;
    private String requestTime;
    private String version;
    private String notifyUrl;
    private String reqContent;
}
