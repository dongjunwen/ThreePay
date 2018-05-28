package com.three.pay.paymentjdbc.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Component
@Table(name = "mer_order")
public class MerOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "pay_no")
  private String payNo;
  @Column(name = "pay_time")
  private java.sql.Timestamp payTime;
  @Column(name = "trade_no")
  private String tradeNo;
  @Column(name = "mer_no")
  private String merNo;
  @Column(name = "mer_name")
  private String merName;
  @Column(name = "mer_order_no")
  private String merOrderNo;
  @Column(name = "mer_pay_seq")
  private String merPaySeq;
  @Column(name = "product_no")
  private String productNo;
  @Column(name = "pay_status")
  private long payStatus;
  @Column(name = "pay_amt")
  private BigDecimal payAmt;
  @Column(name = "order_amt")
  private BigDecimal orderAmt;
  @Column(name = "discount_amt")
  private BigDecimal discountAmt;
  @Column(name = "refund_amt")
  private BigDecimal refundAmt;
  @Column(name = "user_no")
  private String userNo;
  @Column(name = "equip_type")
  private String equipType;
  @Column(name = "equipIp")
  private String equipIp;
  @Column(name = "equipNo")
  private String equipNo;
  private String resv1;
  private String resv2;
  private String resv3;
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

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
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

    public String getMerOrderNo() {
        return merOrderNo;
    }

    public void setMerOrderNo(String merOrderNo) {
        this.merOrderNo = merOrderNo;
    }

    public String getMerPaySeq() {
        return merPaySeq;
    }

    public void setMerPaySeq(String merPaySeq) {
        this.merPaySeq = merPaySeq;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    public String getEquipIp() {
        return equipIp;
    }

    public void setEquipIp(String equipIp) {
        this.equipIp = equipIp;
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

    public String getResv3() {
        return resv3;
    }

    public void setResv3(String resv3) {
        this.resv3 = resv3;
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

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public long getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(long payStatus) {
        this.payStatus = payStatus;
    }

    public BigDecimal getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(BigDecimal payAmt) {
        this.payAmt = payAmt;
    }

    public BigDecimal getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(BigDecimal orderAmt) {
        this.orderAmt = orderAmt;
    }

    public BigDecimal getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(BigDecimal discountAmt) {
        this.discountAmt = discountAmt;
    }

    public BigDecimal getRefundAmt() {
        return refundAmt;
    }

    public void setRefundAmt(BigDecimal refundAmt) {
        this.refundAmt = refundAmt;
    }

    public String getEquipNo() {
        return equipNo;
    }

    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo;
    }
}
