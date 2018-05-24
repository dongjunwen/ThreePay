package com.three.pay.paymentjdbc.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Component
public class PayTradeTotal {
  @Id
  @GeneratedValue
  private long id;
  private String tradeNo;
  private java.sql.Timestamp tradeTime;
  private String merNo;
  private String merName;
  private String userNo;
  private long tradeType;
  private double tradeAmt;
  private double actTradeAmt;
  private String tradeStatus;
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


  public java.sql.Timestamp getTradeTime() {
    return tradeTime;
  }

  public void setTradeTime(java.sql.Timestamp tradeTime) {
    this.tradeTime = tradeTime;
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


  public String getUserNo() {
    return userNo;
  }

  public void setUserNo(String userNo) {
    this.userNo = userNo;
  }


  public long getTradeType() {
    return tradeType;
  }

  public void setTradeType(long tradeType) {
    this.tradeType = tradeType;
  }


  public double getTradeAmt() {
    return tradeAmt;
  }

  public void setTradeAmt(double tradeAmt) {
    this.tradeAmt = tradeAmt;
  }


  public double getActTradeAmt() {
    return actTradeAmt;
  }

  public void setActTradeAmt(double actTradeAmt) {
    this.actTradeAmt = actTradeAmt;
  }


  public String getTradeStatus() {
    return tradeStatus;
  }

  public void setTradeStatus(String tradeStatus) {
    this.tradeStatus = tradeStatus;
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
