package com.three.pay.paymentweb.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author:luiz
 * @Date: 2018/5/30 10:53
 * @Descripton:用户发起退款
 * @Modify :
 **/
@Getter
@Setter
@ToString
public class RefundForm {
    private String refundNo;
    private String merOrderNo;
    private String merPaySeq;
    private String refundAmt;
    private String refundDesc;
}
