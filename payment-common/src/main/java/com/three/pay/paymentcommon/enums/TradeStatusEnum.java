package com.three.pay.paymentcommon.enums;

/**
 * @Author:luiz
 * @Date: 2018/5/28 10:14
 * @Descripton:0:交易中 1:交易成功 2:交易失败 3:交易关闭
 * @Modify :
 **/
public enum TradeStatusEnum {
    INIT("0","交易中"),
    SUCCESS("1","交易成功"),
    FAIL("2","交易失败"),
    CLOSE("3","交易关闭"),
    ;
    private String code;
    private String name;

    TradeStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static final TradeStatusEnum parse(String code){
        for(TradeStatusEnum statusEnum : TradeStatusEnum.values()){
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
