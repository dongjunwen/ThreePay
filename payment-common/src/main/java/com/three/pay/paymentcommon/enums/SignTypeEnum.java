package com.three.pay.paymentcommon.enums;

/**
 * @Author:luiz
 * @Date: 2018/5/28 10:14
 * @Descripton:
 * @Modify :
 **/
public enum SignTypeEnum {
    MD5("MD5","MD5签名"),
    RSA("RSA","RSA签名"),
    RSA2("RSA2","RSA2签名");

    private String code;
    private String name;

    SignTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static final SignTypeEnum parse(String code){
        for(SignTypeEnum signTypeEnum : SignTypeEnum.values()){
            if(code.equals(signTypeEnum.getCode())) return signTypeEnum;
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
