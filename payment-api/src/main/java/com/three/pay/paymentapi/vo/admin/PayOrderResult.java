package com.three.pay.paymentapi.vo.admin;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @Author:luiz
 * @Date: 2018/6/5 14:10
 * @Descripton:
 * @Modify :
 **/
@Getter
@Setter
@ToString
@ApiModel(value = "支付订单返回结果")
public class PayOrderResult {
    private long id;
    private String payNo;
    private java.sql.Timestamp payTime;
    private String tradeNo;
    private String merNo;
    private String merName;
    private String merOrderNo;
    private String merPaySeq;
    private String productNo;
    private long payStatus;
    private String payStatusName;
    private BigDecimal payAmt;
    private BigDecimal orderAmt;
    private BigDecimal discountAmt;
    private BigDecimal refundAmt;
    private String userNo;
    private String equipType;
    private String equipIp;
    private String equipNo;
    private String resv1;
    private String resv2;
    private String resv3;
    private java.sql.Timestamp createTime;
    private java.sql.Timestamp modiTime;
}
