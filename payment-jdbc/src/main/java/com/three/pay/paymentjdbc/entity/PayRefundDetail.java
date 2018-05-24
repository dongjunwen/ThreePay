package com.three.pay.paymentjdbc.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Component
public class PayRefundDetail {
  @Id
  @GeneratedValue
  private long id;
  private String refundSeqNo;
  private String tradeNo;
  private String paySeqNo;
  private String userNo;
  private String refundWay;
  private double refundAmt;
  private long refundStatus;
  private String refundDesc;
  private java.sql.Timestamp startTime;
  private java.sql.Timestamp endTime;
  private String onceStr;
  private String forwardUrl;
  private String respRefundNo;
  private String merNo;
  private String merName;
  private String channelCode;
  private String channelName;
  private String channelPartenerNo;
  private String channelPatenerName;
  private long version;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp modiTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getRefundSeqNo() {
    return refundSeqNo;
  }

  public void setRefundSeqNo(String refundSeqNo) {
    this.refundSeqNo = refundSeqNo;
  }


  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }


  public String getPaySeqNo() {
    return paySeqNo;
  }

  public void setPaySeqNo(String paySeqNo) {
    this.paySeqNo = paySeqNo;
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


  public double getRefundAmt() {
    return refundAmt;
  }

  public void setRefundAmt(double refundAmt) {
    this.refundAmt = refundAmt;
  }


  public long getRefundStatus() {
    return refundStatus;
  }

  public void setRefundStatus(long refundStatus) {
    this.refundStatus = refundStatus;
  }


  public String getRefundDesc() {
    return refundDesc;
  }

  public void setRefundDesc(String refundDesc) {
    this.refundDesc = refundDesc;
  }


  public java.sql.Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(java.sql.Timestamp startTime) {
    this.startTime = startTime;
  }


  public java.sql.Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(java.sql.Timestamp endTime) {
    this.endTime = endTime;
  }


  public String getOnceStr() {
    return onceStr;
  }

  public void setOnceStr(String onceStr) {
    this.onceStr = onceStr;
  }


  public String getForwardUrl() {
    return forwardUrl;
  }

  public void setForwardUrl(String forwardUrl) {
    this.forwardUrl = forwardUrl;
  }


  public String getRespRefundNo() {
    return respRefundNo;
  }

  public void setRespRefundNo(String respRefundNo) {
    this.respRefundNo = respRefundNo;
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


  public String getChannelCode() {
    return channelCode;
  }

  public void setChannelCode(String channelCode) {
    this.channelCode = channelCode;
  }


  public String getChannelName() {
    return channelName;
  }

  public void setChannelName(String channelName) {
    this.channelName = channelName;
  }


  public String getChannelPartenerNo() {
    return channelPartenerNo;
  }

  public void setChannelPartenerNo(String channelPartenerNo) {
    this.channelPartenerNo = channelPartenerNo;
  }


  public String getChannelPatenerName() {
    return channelPatenerName;
  }

  public void setChannelPatenerName(String channelPatenerName) {
    this.channelPatenerName = channelPatenerName;
  }


  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
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
