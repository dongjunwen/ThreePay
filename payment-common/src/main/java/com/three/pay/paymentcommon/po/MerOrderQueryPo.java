package com.three.pay.paymentcommon.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
public class MerOrderQueryPo {
    @NotNull(message = "订单列表不能为空")
    @ApiModelProperty(value = "订单列表",required =true )
    private List<MerPaySeqPo> orderList;

}
