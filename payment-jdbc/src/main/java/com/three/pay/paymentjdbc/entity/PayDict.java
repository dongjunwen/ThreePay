package com.three.pay.paymentjdbc.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "pay_dict")
public class PayDict {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "dict_code")
  private String dictCode;
  @Column(name = "dict_value")
  private String dictValue;
  @Column(name = "dict_name")
  private String dictName;
  @Column(name = "dict_desc")
  private String dictDesc;
  @Column(name = "pdict_code")
  private String pdictCode;
  @Column(name = "status")
  private String status;
  @Column(name = "memo")
  private String memo;
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


  public String getDictCode() {
    return dictCode;
  }

  public void setDictCode(String dictCode) {
    this.dictCode = dictCode;
  }


  public String getDictValue() {
    return dictValue;
  }

  public void setDictValue(String dictValue) {
    this.dictValue = dictValue;
  }


  public String getDictName() {
    return dictName;
  }

  public void setDictName(String dictName) {
    this.dictName = dictName;
  }


  public String getDictDesc() {
    return dictDesc;
  }

  public void setDictDesc(String dictDesc) {
    this.dictDesc = dictDesc;
  }


  public String getPdictCode() {
    return pdictCode;
  }

  public void setPdictCode(String pdictCode) {
    this.pdictCode = pdictCode;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
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
