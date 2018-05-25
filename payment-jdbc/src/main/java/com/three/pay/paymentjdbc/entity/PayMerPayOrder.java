package com.three.pay.paymentjdbc.entity;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Component
@Table(name = "pay_mer_pay_order")
public class PayMerPayOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "pay_no")
  private String payNo;
  @Column(name = "pay_time")
  private java.sql.Timestamp payTime;
  @Column(name = "trade_no")
  private String tradeNo;
  @Column(name = "mer_no")
  private String merNo;
  @Column(name = "mer_name")
  private String merName;
  @Column(name = "mer_order_no")
  private String merOrderNo;
  @Column(name = "mer_pay_seq")
  private String merPaySeq;
  @Column(name = "user_no")
  private String userNo;
  @Column(name = "pay_way")
  private String payWay;
  @Column(name = "pay_status")
  private long payStatus;
  @Column(name = "pay_amt")
  private BigDecimal payAmt;
  @Column(name = "refund_amt")
  private BigDecimal refundAmt;
  @Column(name = "create_time")
  private java.sql.Timestamp createTime;
  @Column(name = "modi_time")
  private java.sql.Timestamp modiTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getPayNo() {
    return payNo;
  }

  public void setPayNo(String payNo) {
    this.payNo = payNo;
  }


  public java.sql.Timestamp getPayTime() {
    return payTime;
  }

  public void setPayTime(java.sql.Timestamp payTime) {
    this.payTime = payTime;
  }


  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }


  public String getMerNo() {
    return merNo;
  }

  public void setMerNo(String merNo) {
    this.merNo = merNo;
  }


  public String getMerName() {
    return merName;
  }

  public void setMerName(String merName) {
    this.merName = merName;
  }


  public String getMerOrderNo() {
    return merOrderNo;
  }

  public void setMerOrderNo(String merOrderNo) {
    this.merOrderNo = merOrderNo;
  }


  public String getMerPaySeq() {
    return merPaySeq;
  }

  public void setMerPaySeq(String merPaySeq) {
    this.merPaySeq = merPaySeq;
  }


  public String getUserNo() {
    return userNo;
  }

  public void setUserNo(String userNo) {
    this.userNo = userNo;
  }


  public String getPayWay() {
    return payWay;
  }

  public void setPayWay(String payWay) {
    this.payWay = payWay;
  }


  public long getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(long payStatus) {
    this.payStatus = payStatus;
  }

  public BigDecimal getPayAmt() {
    return payAmt;
  }

  public void setPayAmt(BigDecimal payAmt) {
    this.payAmt = payAmt;
  }

  public BigDecimal getRefundAmt() {
    return refundAmt;
  }

  public void setRefundAmt(BigDecimal refundAmt) {
    this.refundAmt = refundAmt;
  }

  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getModiTime() {
    return modiTime;
  }

  public void setModiTime(java.sql.Timestamp modiTime) {
    this.modiTime = modiTime;
  }

}
