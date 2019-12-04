package com.three.pay.paymentweb.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author:luiz
 * @Date: 2018/5/30 10:53
 * @Descripton:用户下单
 * @Modify :
 **/
@Getter
@Setter
@ToString
public class OrderForm {
    private String merOrderNo;
    private String goodsName;
    private String goodsDesc;
    private String payAmt;
}
