package com.three.pay.paymentcommon.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

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
public class MerPaySeqPo {
    @NotBlank(message = "商户支付流水号不能为空")
    @Length(min = 1,max = 64,message = "商户支付流水号不能超过64")
    @ApiModelProperty(value = "商户支付流水号",required =true )
    private String merPaySeq;
    @NotBlank(message = "商户订单号不能为空")
    @Length(min = 1,max = 64,message = "商户订单号不能超过64")
    @ApiModelProperty(value = "商户订单号",required =true )
    private String orderNo;
}
