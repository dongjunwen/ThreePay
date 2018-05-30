package com.three.pay.paymentchannel.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author:luiz
 * @Date: 2018/5/28 11:21
 * @Descripton:渠道层统一响应出参
 * @Modify :
 **/
@Getter
@Setter
@ToString
public class ChannelRespParam {
    //响应码
    private String respCode;
    //响应消息
    private String respMsg;
    //响应内容
    private String respContent;

}
