package com.three.pay.paymentcommon.po.notify;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author:luiz
 * @Date: 2018/5/30 15:09
 * @Descripton:
 * @Modify :
 **/
@Getter
@Setter
@ToString
public class NotifyPayParamPo {
    //第三方返回交易号
    private String thirdTradeNo;
    //第三方返回状态
    private String thirdTradeStatus;
    //第三方订单创建时间
    private Date thirdCreateTime;
    //第三方订单支付时间
    private Date thirdPayTime;
    //买家可开票金额
    private BigDecimal buyInvoiceAmt;
    //用户实际付款金额
    private BigDecimal buyPyAmt;
    //第三方优惠金额
    private BigDecimal thirdDiscountAmt;
    //商户实收金额
    private BigDecimal merRecvAmt;
    //卖家编号
    private String sellerId;
    //付款方编号
    private String buyllerId;
    //发送到第三方的交易号
    private String paySeqNo;
    //转换后的状态
    private long tradeStatus;
    //第三方通知内容
    private String thirdNotifyContent;


}
