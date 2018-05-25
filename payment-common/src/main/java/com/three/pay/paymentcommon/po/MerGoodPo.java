package com.three.pay.paymentcommon.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Author:luiz
 * @Date: 2018/5/25 15:34
 * @Descripton:
 * @Modify :
 **/
@Getter
@Setter
@ToString
public class MerGoodPo {
    @NotBlank(message = "商户订单号不能为空")
    @Length(min = 1,max = 64,message = "商户订单号不能超过64")
    @ApiModelProperty(value = "商户订单号",required =true )
    private String orderNo;
    @NotBlank(message = "货物编号不能为空")
    @Length(max = 32,message = "货物编号不能超过32")
    @ApiModelProperty(value = "货物编号",required =true )
    private String goodsId;
    @NotBlank(message = "货货物描述不能为空")
    @Length(max = 256,message = "货物描述不能超过256")
    @ApiModelProperty(value = "货物描述",required =true )
    private String goodsDesc;
    @NotBlank(message = "商品单价不能为空")
    @Length(max = 9,message = "商品单价不能超过9")
    @ApiModelProperty(value = "商品单价",required =true )
    private String goodsPrice;
    @NotBlank(message = "商品数量不能为空")
    @Length(max = 9,message = "商品数量不能超过9")
    @ApiModelProperty(value = "商品数量",required =true )
    private String goodsNum;
    @NotBlank(message = "商品单位不能为空")
    @Length(max = 10,message = "商品单位不能超过10")
    @ApiModelProperty(value = "商品单位",required =true )
    private String goodsUnit;
}
