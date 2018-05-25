package com.three.pay.paymentjdbc.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Component
@Table(name = "pay_trade_total")
public class PayTradeTotal {
  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  private long id;
  @Column(name = "trade_no")
  private String tradeNo;
  @Column(name = "trade_time")
  private java.sql.Timestamp tradeTime;
  @Column(name = "mer_no")
  private String merNo;
  @Column(name = "mer_name")
  private String merName;
  @Column(name="user_no")
  private String userNo;
  @Column(name = "trade_type")
  private long tradeType;
  @Column(name = "trade_amt")
  private BigDecimal tradeAmt;
  @Column(name = "act_trade_amt")
  private BigDecimal actTradeAmt;
  @Column(name = "trade_status")
  private String tradeStatus;
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


  public BigDecimal getTradeAmt() {
    return tradeAmt;
  }

  public void setTradeAmt(BigDecimal tradeAmt) {
    this.tradeAmt = tradeAmt;
  }

  public BigDecimal getActTradeAmt() {
    return actTradeAmt;
  }

  public void setActTradeAmt(BigDecimal actTradeAmt) {
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
