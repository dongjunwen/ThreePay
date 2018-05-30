package com.three.pay.paymentcommon.po.notify;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author:luiz
 * @Date: 2018/5/30 15:09
 * @Descripton:
 * @Modify :
 **/
@Getter
@Setter
@ToString
public class NotifyPayParamPo {
    //第三方返回交易号
    private String thirdTradeNo;
    //第三方返回状态
    private String thirdTradeStatus;
    //发送到第三方的交易号
    private String paySeqNo;
    //转换后的状态
    private long tradeStatus;
}
