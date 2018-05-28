package com.three.pay.paymentcommon.enums;

/**
 * @Author:luiz
 * @Date: 2018/5/28 10:14
 * @Descripton:
 * @Modify :
 **/
public enum TradeTypeEnum {
    PAY(0,"付款"),
    REFUND(1,"退款");

    private int code;
    private String name;

    TradeTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static final TradeTypeEnum parse(int code){
        for(TradeTypeEnum statusEnum : TradeTypeEnum.values()){
            if(code==statusEnum.getCode()) return statusEnum;
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
