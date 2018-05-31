package com.three.pay.paymentjdbc.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "pay_req_log")
public class PayReqLog {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "trade_no")
  private String tradeNo;
  @Column(name = "trade_type")
  private int tradeType;
  @Column(name = "pay_way")
  private String payWay;
  @Column(name = "pay_way_name")
  private String payWayName;
  @Column(name = "req_content")
  private String reqContent;
  @Column(name = "syn_content")
  private String synContent;
  @Column(name = "create_time")
  private java.sql.Timestamp createTime;


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


  public int getTradeType() {
    return tradeType;
  }

  public void setTradeType(int tradeType) {
    this.tradeType = tradeType;
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


  public String getReqContent() {
    return reqContent;
  }

  public void setReqContent(String reqContent) {
    this.reqContent = reqContent;
  }


  public String getSynContent() {
    return synContent;
  }

  public void setSynContent(String synContent) {
    this.synContent = synContent;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
