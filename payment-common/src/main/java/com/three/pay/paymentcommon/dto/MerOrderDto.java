package com.three.pay.paymentcommon.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @Author:luiz
 * @Date: 2018/5/28 15:28
 * @Descripton:
 * @Modify :
 **/
@Setter
@Getter
@ToString
public class MerOrderDto {

    private String merNo;
    private String merName;
    private BigDecimal merFee;
    private String productNo;
    private String productName;
    private String channelCode;
    private String channelName;
    private String payWay;
    private String payWayName;
    private String signType;
    private String pubKey;
    private String privKey;
    private String resvKey;
    private String resv1;
    private String resv2;
    private String status;
    private BigDecimal channelFee;
    private String channelPartenerNo;
    private String channelPartenerName;
    /**
     * 本系统交易号
     */
    private String innerTradeNo;
    /**
     * 本系统流水号
     */
    private String innerSeqNo;
}
