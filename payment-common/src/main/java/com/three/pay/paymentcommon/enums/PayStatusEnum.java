package com.three.pay.paymentcommon.enums;

/**
 * @Author:luiz
 * @Date: 2018/5/28 10:14
 * @Descripton:付款状态 0:支付中 1:支付成功 2:支付失败 3:交易关闭
 * @Modify :
 **/
public enum PayStatusEnum {
    PAY_INIT(0,"支付中"),
    PAY_SUCCESS(1,"支付成功"),
    PAY_FAIL(2,"支付失败"),
    PAY_CLOSE(3,"支付关闭"),
    ;

    private int code;
    private String name;

    PayStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static final PayStatusEnum parse(int code){
        for(PayStatusEnum statusEnum : PayStatusEnum.values()){
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
