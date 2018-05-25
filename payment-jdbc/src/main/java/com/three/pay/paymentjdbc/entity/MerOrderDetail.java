package com.three.pay.paymentjdbc.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Component
@Table(name = "mer_order_detail")
public class MerOrderDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "mer_order_no")
  private String merOrderNo;
  @Column(name = "goods_id")
  private String goodsId;
  @Column(name = "goods_desc")
  private String goodsDesc;
  @Column(name = "goods_price")
  private BigDecimal goodsPrice;
  @Column(name = "goods_unit")
  private String goodsUnit;
  @Column(name = "goods_amt")
  private BigDecimal goodsAmt;
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


  public String getMerOrderNo() {
    return merOrderNo;
  }

  public void setMerOrderNo(String merOrderNo) {
    this.merOrderNo = merOrderNo;
  }


  public String getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(String goodsId) {
    this.goodsId = goodsId;
  }


  public String getGoodsDesc() {
    return goodsDesc;
  }

  public void setGoodsDesc(String goodsDesc) {
    this.goodsDesc = goodsDesc;
  }


  public BigDecimal getGoodsPrice() {
    return goodsPrice;
  }

  public void setGoodsPrice(BigDecimal goodsPrice) {
    this.goodsPrice = goodsPrice;
  }


  public String getGoodsUnit() {
    return goodsUnit;
  }

  public void setGoodsUnit(String goodsUnit) {
    this.goodsUnit = goodsUnit;
  }


  public BigDecimal getGoodsAmt() {
    return goodsAmt;
  }

  public void setGoodsAmt(BigDecimal goodsAmt) {
    this.goodsAmt = goodsAmt;
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
