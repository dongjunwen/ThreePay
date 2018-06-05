package com.three.pay.paymentapi.vo.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
 * @Author:luiz
 * @Date: 2018/6/4 10:56
 * @Descripton:2018-06-04 13:00:00
 * @Modify :
 **/
@ApiModel(value = "支付订单查询条件")
public class PayOrderCondParam extends PageParam {

    //交易开始时间
    @Length(min=10,max = 10,message = "交易开始时间格式不正确")
    @ApiModelProperty(value = "交易开始时间",required =false )
    private String tradeBeginDate;
    //交易结束时间
    @Length(min=10,max = 10,message = "交易结束时间格式不正确")
    @ApiModelProperty(value = "交易结束时间",required =false )
    private String tradeEndDate;
    //交易商户号
    @Length(max = 64,message = "商户号不能超过64")
    @ApiModelProperty(value = "商户号",required =false )
    private String merNo;
    //商户订单号
    @Length(max = 32,message = "商户号不能超过32")
    @ApiModelProperty(value = "商户订单号",required =false )
    private String merOrderNo;
    //支付订单号
    @Length(max = 32,message = "支付交易号不能超过32")
    @ApiModelProperty(value = "支付交易号",required =false )
    private String payTradeNo;

    public String getTradeBeginDate() {
        tradeBeginDate=tradeBeginDate!=null?tradeBeginDate+" 00:00:00":tradeBeginDate;
        return tradeBeginDate;
    }

    public String getTradeEndDate() {
        tradeEndDate=tradeEndDate!=null?tradeEndDate+" 23:59:59":tradeEndDate;
        return tradeEndDate;
    }

    public String getMerNo() {
        return merNo;
    }

    public String getMerOrderNo() {
        return merOrderNo;
    }

    public String getPayTradeNo() {
        return payTradeNo;
    }

    public void setTradeBeginDate(String tradeBeginDate) {
        this.tradeBeginDate = tradeBeginDate;
    }

    public void setTradeEndDate(String tradeEndDate) {
        this.tradeEndDate = tradeEndDate;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }

    public void setMerOrderNo(String merOrderNo) {
        this.merOrderNo = merOrderNo;
    }

    public void setPayTradeNo(String payTradeNo) {
        this.payTradeNo = payTradeNo;
    }
}
