package com.three.pay.paymentcommon.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

/**
 * @Author:luiz
 * @Date: 2018/5/25 15:35
 * @Descripton:
 * @Modify :
 **/
@Getter
@Setter
@ToString
public class MerRefundOrderPo {
    @NotBlank(message = "商户支付流水号不能为空")
    @Length(min = 1,max = 64,message = "商户支付流水号不能超过64")
    @ApiModelProperty(value = "商户支付流水号",required =true )
    private String merPaySeq;
    @NotBlank(message = "商户订单号不能为空")
    @Length(min = 1,max = 64,message = "商户订单号不能超过64")
    @ApiModelProperty(value = "商户订单号",required =true )
    private String merOrderNo;
    @NotBlank(message = "退款金额不能为空")
    @Length(min = 1,max = 9,message = "订单金额不能超过9")
    @DecimalMin(value = "0.01",message = "退款金额最小值为0.01")
    @ApiModelProperty(value = "退款金额",required =true )
    private String refundAmt;
    @NotBlank(message = "退款描述不能为空")
    @Length(min = 1,max = 128,message = "退款描述不能超过128")
    @ApiModelProperty(value = "退款描述",required =true )
    private String refundDesc;
}
