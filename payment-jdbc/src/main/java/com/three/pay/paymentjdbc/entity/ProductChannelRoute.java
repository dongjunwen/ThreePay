package com.three.pay.paymentjdbc.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Component
@Table(name = "product_channel_route")
public class ProductChannelRoute {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "mer_no")
  private String merNo;
  @Column(name = "product_no")
  private String productNo;
  @Column(name = "mer_fee")
  private BigDecimal merFee;
  @Column(name = "pay_way")
  private String payWay;
  @Column(name = "use_level")
  private long useLevel;
  @Column(name = "status")
  private String status;
  @Column(name = "channel_partener_no")
  private String channelPartenerNo;
  @Column(name = "channel_partener_name")
  private String channelPartenrName;
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

  public String getMerNo() {
    return merNo;
  }

  public void setMerNo(String merNo) {
    this.merNo = merNo;
  }

  public String getProductNo() {
    return productNo;
  }

  public void setProductNo(String productNo) {
    this.productNo = productNo;
  }

  public BigDecimal getMerFee() {
    return merFee;
  }

  public void setMerFee(BigDecimal merFee) {
    this.merFee = merFee;
  }

  public String getPayWay() {
    return payWay;
  }

  public void setPayWay(String payWay) {
    this.payWay = payWay;
  }

  public long getUseLevel() {
    return useLevel;
  }

  public void setUseLevel(long useLevel) {
    this.useLevel = useLevel;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getChannelPartenerNo() {
    return channelPartenerNo;
  }

  public void setChannelPartenerNo(String channelPartenerNo) {
    this.channelPartenerNo = channelPartenerNo;
  }

  public String getChannelPartenrName() {
    return channelPartenrName;
  }

  public void setChannelPartenrName(String channelPartenrName) {
    this.channelPartenrName = channelPartenrName;
  }

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  public Timestamp getModiTime() {
    return modiTime;
  }

  public void setModiTime(Timestamp modiTime) {
    this.modiTime = modiTime;
  }
}
