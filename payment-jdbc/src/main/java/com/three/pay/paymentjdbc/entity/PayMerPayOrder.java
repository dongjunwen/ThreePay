package com.three.pay.paymentjdbc.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Component
public class PayMerPayOrder {
  @Id
  @GeneratedValue
  private long id;
  private String payNo;
  private java.sql.Timestamp payTime;
  private String tradeNo;
  private String merNo;
  private String merName;
  private String merOrderNo;
  private String merPaySeq;
  private String userNo;
  private String payWay;
  private long payStatus;
  private double payAmt;
  private double refundAmt;
  private java.sql.Timestamp createTime;
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


  public double getPayAmt() {
    return payAmt;
  }

  public void setPayAmt(double payAmt) {
    this.payAmt = payAmt;
  }


  public double getRefundAmt() {
    return refundAmt;
  }

  public void setRefundAmt(double refundAmt) {
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
