package com.three.pay.paymentjdbc.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Component
public class ChannelDetail {

  @Id
  @GeneratedValue
  private long id;
  private String payWay;
  private String payWayName;
  private String channelCode;
  private String signType;
  private String pubKey;
  private String privKey;
  private String resvKey;
  private String resv1;
  private String resv2;
  private String status;
  private long version;
  private java.sql.Timestamp createTime;
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

}
