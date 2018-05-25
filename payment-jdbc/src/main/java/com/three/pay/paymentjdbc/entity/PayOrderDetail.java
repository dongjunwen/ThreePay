package com.three.pay.paymentjdbc.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Component
@Table(name = "pay_order_detail")
public class PayOrderDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "trade_no")
  private String tradeNo;
  @Column(name = "pay_time")
  private java.sql.Timestamp payTime;
  @Column(name = "pay_seq_no")
  private String paySeqNo;
  @Column(name = "pay_way")
  private String payWay;
  @Column(name = "pay_status")
  private long payStatus;
  @Column(name = "pay_success_time")
  private java.sql.Timestamp paySuccessTime;
  @Column(name = "refund_onway_amt")
  private BigDecimal refundOnwayAmt;
  @Column(name = "refund_success_amt")
  private BigDecimal refundSuccessAmt;
  @Column(name = "pay_amt")
  private BigDecimal payAmt;
  @Column(name = "user_no")
  private String userNo;
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
  @Column(name = "channel_patener_name")
  private String channelPatenerName;
  @Column(name = "goods_name")
  private String goodsName;
  @Column(name = "goods_desc")
  private String goodsDesc;
  @Column(name = "currency_type")
  private String currencyType;
  @Column(name = "pay_fee")
  private double payFee;
  @Column(name = "start_time")
  private java.sql.Timestamp startTime;
  @Column(name = "end_time")
  private java.sql.Timestamp endTime;
  @Column(name = "payler_acct")
  private String paylerAcct;
  @Column(name = "seller_acct")
  private String sellerAcct;
  @Column(name = "equip_type")
  private String equipType;
  @Column(name = "equip_no")
  private String equipNo;
  @Column(name = "equip_ip")
  private String equipIp;
  @Column(name = "once_str")
  private String onceStr;
  @Column(name = "forward_url")
  private String forwardUrl;
  @Column(name = "resp_pay_no")
  private String respPayNo;
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


  public BigDecimal getRefundOnwayAmt() {
    return refundOnwayAmt;
  }

  public void setRefundOnwayAmt(BigDecimal refundOnwayAmt) {
    this.refundOnwayAmt = refundOnwayAmt;
  }

  public BigDecimal getRefundSuccessAmt() {
    return refundSuccessAmt;
  }

  public void setRefundSuccessAmt(BigDecimal refundSuccessAmt) {
    this.refundSuccessAmt = refundSuccessAmt;
  }

  public BigDecimal getPayAmt() {
    return payAmt;
  }

  public void setPayAmt(BigDecimal payAmt) {
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
