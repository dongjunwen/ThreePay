package com.three.pay.paymentjdbc.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Component
@Table(name = "pay_refund_detail")
public class PayRefundDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "refund_seq_no")
  private String refundSeqNo;
  @Column(name = "trade_no")
  private String tradeNo;
  @Column(name = "pay_seq_no")
  private String paySeqNo;
  @Column(name = "user_no")
  private String userNo;
  @Column(name = "refund_way")
  private String refundWay;
  @Column(name = "refund_amt")
  private BigDecimal refundAmt;
  @Column(name = "refund_status")
  private long refundStatus;
  @Column(name = "refund_desc")
  private String refundDesc;
  @Column(name = "start_time")
  private java.sql.Timestamp startTime;
  @Column(name = "end_time")
  private java.sql.Timestamp endTime;
  @Column(name = "once_str")
  private String onceStr;
  @Column(name = "forward_url")
  private String forwardUrl;

  @Column(name = "payler_acct")
  private String paylerAcct;
  @Column(name = "seller_acct")
  private String sellerAcct;
  @Column(name = "refund_currency")
  private String refundCurrency;
  @Column(name = "refund_success_time")
  private java.sql.Timestamp refundSuccessTime;
  @Column(name = "buy_refund_amt")
  private BigDecimal buyRefundAmt;
  @Column(name = "third_refund_discount_amt")
  private BigDecimal thirdRefundDiscountAmt;
  @Column(name = "mer_refund_discount_amt")
  private BigDecimal merRefundDiscountAmt;

  @Column(name = "resp_refund_no")
  private String respRefundNo;
  @Column(name = "mer_no")
  private String merNo;
  @Column(name = "mer_name")
  private String merName;
  @Column(name = "channel_code")
  private String channelCode;
  @Column(name = "channel_name")
  private String channelName;
  @Column(name = "channel_partener_no")
  private String channelPartenerNo;
  @Column(name = "channel_partener_name")
  private String channelPartenerName;
  @Column(name = "version")
  private long version;
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


  public BigDecimal getRefundAmt() {
    return refundAmt;
  }

  public void setRefundAmt(BigDecimal refundAmt) {
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


  public String getChannelPartenerName() {
    return channelPartenerName;
  }

  public void setChannelPartenerName(String channelPatenerName) {
    this.channelPartenerName = channelPatenerName;
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

  public String getRefundCurrency() {
    return refundCurrency;
  }

  public void setRefundCurrency(String refundCurrency) {
    this.refundCurrency = refundCurrency;
  }

  public Timestamp getRefundSuccessTime() {
    return refundSuccessTime;
  }

  public void setRefundSuccessTime(Timestamp refundSuccessTime) {
    this.refundSuccessTime = refundSuccessTime;
  }

  public BigDecimal getBuyRefundAmt() {
    return buyRefundAmt;
  }

  public void setBuyRefundAmt(BigDecimal buyRefundAmt) {
    this.buyRefundAmt = buyRefundAmt;
  }

  public BigDecimal getThirdRefundDiscountAmt() {
    return thirdRefundDiscountAmt;
  }

  public void setThirdRefundDiscountAmt(BigDecimal thirdRefundDiscountAmt) {
    this.thirdRefundDiscountAmt = thirdRefundDiscountAmt;
  }

  public BigDecimal getMerRefundDiscountAmt() {
    return merRefundDiscountAmt;
  }

  public void setMerRefundDiscountAmt(BigDecimal merRefundDiscountAmt) {
    this.merRefundDiscountAmt = merRefundDiscountAmt;
  }
}
