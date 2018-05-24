package com.three.pay.paymentjdbc.entity;


import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
@Component
public class PayDict {
  @Id
  @GeneratedValue
  private long id;
  private String dictCode;
  private String dictValue;
  private String dictName;
  private String dictDesc;
  private String pdictCode;
  private String status;
  private String memo;
  private java.sql.Timestamp createTime;
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
