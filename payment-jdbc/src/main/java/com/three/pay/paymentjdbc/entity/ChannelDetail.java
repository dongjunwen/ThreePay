package com.three.pay.paymentjdbc.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Component
@Table(name = "channel_detail")
public class ChannelDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "pay_way")
  private String payWay;
  @Column(name = "pay_way_name")
  private String payWayName;
  @Column(name = "channel_code")
  private String channelCode;
  @Column(name = "sign_type")
  private String signType;
  @Column(name = "pub_key")
  private String pubKey;
  @Column(name = "priv_key")
  private String privKey;
  @Column(name = "resv_key")
  private String resvKey;
  private String resv1;
  private String resv2;
  private String status;
  @Column(name = "channel_fee")
  private BigDecimal channelFee;
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


  public String getPayWay() {
    return payWay;
  }

  public void setPayWay(String payWay) {
    this.payWay = payWay;
  }


  public String getPayWayName() {
    return payWayName;
  }

  public void setPayWayName(String payWayName) {
    this.payWayName = payWayName;
  }


  public String getChannelCode() {
    return channelCode;
  }

  public void setChannelCode(String channelCode) {
    this.channelCode = channelCode;
  }


  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
  }


  public String getPubKey() {
    return pubKey;
  }

  public void setPubKey(String pubKey) {
    this.pubKey = pubKey;
  }


  public String getPrivKey() {
    return privKey;
  }

  public void setPrivKey(String privKey) {
    this.privKey = privKey;
  }


  public String getResvKey() {
    return resvKey;
  }

  public void setResvKey(String resvKey) {
    this.resvKey = resvKey;
  }


  public String getResv1() {
    return resv1;
  }

  public void setResv1(String resv1) {
    this.resv1 = resv1;
  }


  public String getResv2() {
    return resv2;
  }

  public void setResv2(String resv2) {
    this.resv2 = resv2;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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

  public BigDecimal getChannelFee() {
    return channelFee;
  }

  public void setChannelFee(BigDecimal channelFee) {
    this.channelFee = channelFee;
  }
}
