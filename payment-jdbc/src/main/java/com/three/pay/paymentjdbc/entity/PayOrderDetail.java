package com.three.pay.paymentjdbc.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Component
public class PayOrderDetail {
  @Id
  @GeneratedValue
  private long id;
  private String tradeNo;
  private java.sql.Timestamp payTime;
  private String paySeqNo;
  private String payWay;
  private long payStatus;
  private java.sql.Timestamp paySuccessTime;
  private double refundOnwayAmt;
  private double refundSuccessAmt;
  private double payAmt;
  private String userNo;
  private String merNo;
  private String merName;
  private String channelCode;
  private String channelName;
  private String channelPartenerNo;
  private String channelPatenerName;
  private String goodsName;
  private String goodsDesc;
  private String currencyType;
  private double payFee;
  private java.sql.Timestamp startTime;
  private java.sql.Timestamp endTime;
  private String paylerAcct;
  private String sellerAcct;
  private String equipType;
  private String equipNo;
  private String equipIp;
  private String onceStr;
  private String forwardUrl;
  private String respPayNo;
  private long version;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp modiTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }


  public java.sql.Timestamp getPayTime() {
    return payTime;
  }

  public void setPayTime(java.sql.Timestamp payTime) {
    this.payTime = payTime;
  }


  public String getPaySeqNo() {
    return paySeqNo;
  }

  public void setPaySeqNo(String paySeqNo) {
    this.paySeqNo = paySeqNo;
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


  public java.sql.Timestamp getPaySuccessTime() {
    return paySuccessTime;
  }

  public void setPaySuccessTime(java.sql.Timestamp paySuccessTime) {
    this.paySuccessTime = paySuccessTime;
  }


  public double getRefundOnwayAmt() {
    return refundOnwayAmt;
  }

  public void setRefundOnwayAmt(double refundOnwayAmt) {
    this.refundOnwayAmt = refundOnwayAmt;
  }


  public double getRefundSuccessAmt() {
    return refundSuccessAmt;
  }

  public void setRefundSuccessAmt(double refundSuccessAmt) {
    this.refundSuccessAmt = refundSuccessAmt;
  }


  public double getPayAmt() {
    return payAmt;
  }

  public void setPayAmt(double payAmt) {
    this.payAmt = payAmt;
  }


  public String getUserNo() {
    return userNo;
  }

  public void setUserNo(String userNo) {
    this.userNo = userNo;
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


  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }


  public String getGoodsDesc() {
    return goodsDesc;
  }

  public void setGoodsDesc(String goodsDesc) {
    this.goodsDesc = goodsDesc;
  }


  public String getCurrencyType() {
    return currencyType;
  }

  public void setCurrencyType(String currencyType) {
    this.currencyType = currencyType;
  }


  public double getPayFee() {
    return payFee;
  }

  public void setPayFee(double payFee) {
    this.payFee = payFee;
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


  public String getPaylerAcct() {
    return paylerAcct;
  }

  public void setPaylerAcct(String paylerAcct) {
    this.paylerAcct = paylerAcct;
  }


  public String getSellerAcct() {
    return sellerAcct;
  }

  public void setSellerAcct(String sellerAcct) {
    this.sellerAcct = sellerAcct;
  }


  public String getEquipType() {
    return equipType;
  }

  public void setEquipType(String equipType) {
    this.equipType = equipType;
  }


  public String getEquipNo() {
    return equipNo;
  }

  public void setEquipNo(String equipNo) {
    this.equipNo = equipNo;
  }


  public String getEquipIp() {
    return equipIp;
  }

  public void setEquipIp(String equipIp) {
    this.equipIp = equipIp;
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


  public String getRespPayNo() {
    return respPayNo;
  }

  public void setRespPayNo(String respPayNo) {
    this.respPayNo = respPayNo;
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
