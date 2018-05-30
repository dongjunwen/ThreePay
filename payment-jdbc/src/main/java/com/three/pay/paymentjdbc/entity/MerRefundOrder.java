package com.three.pay.paymentjdbc.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Component
@Table(name = "mer_refund_order")
public class MerRefundOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "refund_no")
  private String refundNo;
  @Column(name = "refund_time")
  private java.sql.Timestamp refundTime;
  @Column(name = "pay_no")
  private String payNo;
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
  @Column(name = "refund_way")
  private String refundWay;
  @Column(name = "refund_status")
  private long refundStatus;
  @Column(name = "refund_amt")
  private BigDecimal refundAmt;
  @Column(name = "refund_desc")
  private String refundDesc;
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


  public String getRefundNo() {
    return refundNo;
  }

  public void setRefundNo(String refundNo) {
    this.refundNo = refundNo;
  }


  public java.sql.Timestamp getRefundTime() {
    return refundTime;
  }

  public void setRefundTime(java.sql.Timestamp refundTime) {
    this.refundTime = refundTime;
  }


  public String getPayNo() {
    return payNo;
  }

  public void setPayNo(String payNo) {
    this.payNo = payNo;
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


  public String getRefundWay() {
    return refundWay;
  }

  public void setRefundWay(String refundWay) {
    this.refundWay = refundWay;
  }


  public long getRefundStatus() {
    return refundStatus;
  }

  public void setRefundStatus(long refundStatus) {
    this.refundStatus = refundStatus;
  }


  public BigDecimal getRefundAmt() {
    return refundAmt;
  }

  public void setRefundAmt(BigDecimal refundAmt) {
    this.refundAmt = refundAmt;
  }


  public String getRefundDesc() {
    return refundDesc;
  }

  public void setRefundDesc(String refundDesc) {
    this.refundDesc = refundDesc;
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
