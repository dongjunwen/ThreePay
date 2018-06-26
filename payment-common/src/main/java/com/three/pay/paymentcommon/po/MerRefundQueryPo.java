package com.three.pay.paymentcommon.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Author:luiz
 * @Date: 2018/5/24 17:41
 * @Descripton:退款查询请求参数
 * @Modify :
 **/
@Setter
@Getter
@ToString
public class MerRefundQueryPo {
    @NotBlank(message = "商户退款单号不能为空")
    @Length(min = 1,max = 64,message = "商户退款单号不能超过64")
    @ApiModelProperty(value = "商户退款单号",required =true )
    private String refundNo;
}
