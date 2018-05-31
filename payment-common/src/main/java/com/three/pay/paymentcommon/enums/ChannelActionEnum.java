package com.three.pay.paymentcommon.enums;

/**
@Author:luiz
@Date: 2018/5/25 16:15
@Descripton:渠道层动作
@Modify :
 **/
public enum ChannelActionEnum {

    CREATE_ORDER("CREATE_ORDER","创建订单"),
    ORDER_QEURY("ORDER_QEURY","订单查询"),
    REFUND("REFUND","退款"),
    REFUND_QUERY("REFUND_QUERY","退款查询")
    ;

    private String code;
    private String name;

    ChannelActionEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static final ChannelActionEnum parse(String code){
        for(ChannelActionEnum channelActionEnum : ChannelActionEnum.values()){
            if(code.equals(channelActionEnum.getCode())) return channelActionEnum;
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}