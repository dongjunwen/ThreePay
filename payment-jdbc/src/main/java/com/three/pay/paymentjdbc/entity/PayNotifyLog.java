package com.three.pay.paymentjdbc.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "pay_notify_log")
public class PayNotifyLog {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "third_notify_id")
  private String thirdNotifyId;
  @Column(name = "trade_no")
  private String tradeNo;
  @Column(name = "trade_type")
  private long tradeType;
  @Column(name = "pay_way")
  private String payWay;
  @Column(name = "pay_way_name")
  private String payWayName;
  @Column(name = "notify_content")
  private String notifyContent;
  @Column(name = "create_time")
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getThirdNotifyId() {
    return thirdNotifyId;
  }

  public void setThirdNotifyId(String thirdNotifyId) {
    this.thirdNotifyId = thirdNotifyId;
  }


  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }


  public long getTradeType() {
    return tradeType;
  }

  public void setTradeType(long tradeType) {
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


  public String getNotifyContent() {
    return notifyContent;
  }

  public void setNotifyContent(String notifyContent) {
    this.notifyContent = notifyContent;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
