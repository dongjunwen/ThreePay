package com.three.pay.paymentcommon.enums;

/**
 * @Author:luiz
 * @Date: 2018/5/28 10:14
 * @Descripton:
 * @Modify :
 **/
public enum ChannelCodeEnum {
    SUCCESS("0000","成功"),

    TRADE_NOT_EXIST("1001","查询交易不存在"),
    NOT_ENOUGH("1002","卖家余额不足，导致退款失败，可重新发起"),
    SIGN_VALID_FAIL("9001","签名校验失败"),
    FAIL("9999","失败");

    private String code;
    private String name;

    ChannelCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static final ChannelCodeEnum parse(String code){
        for(ChannelCodeEnum statusEnum : ChannelCodeEnum.values()){
            if(code.equals(statusEnum.getCode())) return statusEnum;
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
