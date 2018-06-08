package com.three.pay.paymentcommon.enums;

/**
 * @Author:luiz
 * @Date: 2018/5/28 10:14
 * @Descripton:退款状态 0:退款中 1:退款成功 2:退款失败
 * @Modify :
 **/
public enum RefundStatusEnum {
    REFUND_INIT(0,"退款中"),
    REFUND_SUCCESS(1,"退款成功"),
    REFUND_FAIL(2,"退款失败"),
    ;

    private int code;
    private String name;

    RefundStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static final RefundStatusEnum parse(int code){
        for(RefundStatusEnum statusEnum : RefundStatusEnum.values()){
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
