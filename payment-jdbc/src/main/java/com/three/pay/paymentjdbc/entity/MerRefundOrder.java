package com.three.pay.paymentjdbc.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Component
public class MerRefundOrder {
  @Id
  @GeneratedValue
  private long id;
  private String refundNo;
  private java.sql.Timestamp refundTime;
  private String payNo;
  private String tradeNo;
  private String merNo;
  private String merName;
  private String merOrderNo;
  private String merPaySeq;
  private String userNo;
  private String refundWay;
  private long refundStatus;
  private double refundAmt;
  private String refundDesc;
  private java.sql.Timestamp createTime;
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


  public double getRefundAmt() {
    return refundAmt;
  }

  public void setRefundAmt(double refundAmt) {
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
