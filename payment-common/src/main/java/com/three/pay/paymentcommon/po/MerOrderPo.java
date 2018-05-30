package com.three.pay.paymentcommon.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author:luiz
 * @Date: 2018/5/24 17:41
 * @Descripton:支付收单请求参数
 * @Modify :
 **/
@Setter
@Getter
@ToString
public class MerOrderPo {

    @NotBlank(message = "商品名称不能为空")
    @Length(min = 1,max = 256,message = "商品名称不能超过256")
    @ApiModelProperty(value = "商品名称",required =true )
    private String goodsName;
    @NotBlank(message = "付款金额不能为空")
    @Length(min = 1,max = 9,message = "付款金额不能超过9")
    @DecimalMin(value = "0.01",message = "付款金额最小值为0.01")
    @ApiModelProperty(value = "付款金额",required =true )
    private String payAmt;
    @Length(max = 9,message = "优惠金额不能超过9")
    @ApiModelProperty(value = "优惠金额",required =false )
    private String discountAmt;
    @NotBlank(message = "订单金额不能为空")
    @Length(min = 1,max = 9,message = "订单金额不能超过9")
    @DecimalMin(value = "0.01",message = "订单金额最小值为0.01")
    @ApiModelProperty(value = "订单金额",required =true )
    private String orderAmt;
    @Length(max = 32,message = "用户编号不能超过32")
    @ApiModelProperty(value = "用户编号",required =false )
    private String userNo;
    @Length(max = 16,message = "设备类型不能超过32")
    @ApiModelProperty(value = "设备类型",required =false )
    private String equipType;
    @Length(max = 16,message = "设备ip不能超过32")
    @ApiModelProperty(value = "设备ip",required =false )
    private String equipIp;
    @Length(max = 64,message = "设备编号不能超过64")
    @ApiModelProperty(value = "设备编号",required =false )
    private String equipNo;
    @Length(max = 256,message = "扩展参数1不能超过256")
    @ApiModelProperty(value = "扩展参数1",required =false )
    private String resv1;
    @Length(max = 256,message = "扩展参数2不能超过256")
    @ApiModelProperty(value = "保留域2",required =false )
    private String resv2;
    @Length(max = 256,message = "扩展参数3不能超过256")
    @ApiModelProperty(value = "保留域3",required =false )
    private String resv3;
    @NotNull(message = "订单列表不能为空")
    @ApiModelProperty(value = "订单列表",required =true )
    private List<MerPaySeqPo> orderList;
    @ApiModelProperty(value = "货物列表",required =false )
    private List<MerGoodPo> goodsList;
}
