package com.three.pay.paymentapi.vo.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * @Author:luiz
 * @Date: 2018/6/4 10:56
 * @Descripton:2018-06-04 13:00:00
 * @Modify :
 **/
@Getter
@Setter
@ToString
@ApiModel(value = "支付订单查询条件")
public class PayOrderCondParam extends PageParam {

    //交易开始时间
    @Length(min=19,max = 19,message = "交易开始时间格式不正确")
    @ApiModelProperty(value = "交易开始时间",required =false )
    private String tradeBeginDate;
    //交易结束时间
    @Length(min=19,max = 19,message = "交易结束时间格式不正确")
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
}
