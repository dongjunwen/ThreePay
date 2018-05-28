package com.three.pay.paymentcommon.enums;

/**
 * @Author:luiz
 * @Date: 2018/5/28 10:14
 * @Descripton:
 * @Modify :
 **/
public enum StatusEnum {
    YES("Y","有效"),
    NO("N","无效");

    private String code;
    private String name;

    StatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static final StatusEnum parse(String code){
        for(StatusEnum statusEnum : StatusEnum.values()){
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
