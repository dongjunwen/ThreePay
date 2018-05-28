package com.three.pay.paymentcommon.enums;

/**
@Author:luiz
@Date: 2018/5/25 16:15
@Descripton:
@Modify :
 **/
public enum PayWayEnum {

    ALIPAY_SCAN_CODE("ALIPAY_SCAN_CODE","支付宝扫码"),
    UNP_SCAN_CODE("UNP_SCAN_CODE","银联扫码"),
    WEICHAT_SCAN_CODE("WEICHAT_SCAN_CODE","微信扫码"),

    ;

    private String code;
    private String name;

    PayWayEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static final PayWayEnum parse(String code){
        for(PayWayEnum payWayEnum : PayWayEnum.values()){
            if(code.equals(payWayEnum.getCode())) return payWayEnum;
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