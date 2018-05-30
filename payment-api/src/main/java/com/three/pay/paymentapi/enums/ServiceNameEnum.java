package com.three.pay.paymentapi.enums;

/**
@Author:luiz
@Date: 2018/5/25 16:15
@Descripton:
@Modify :
 **/
public enum ServiceNameEnum {

    UNION_CREATE_ORDER("UNION_CREATE_ORDER","合并收单接口");

    private String code;
    private String name;

    ServiceNameEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static final ServiceNameEnum parse(String code){
        for(ServiceNameEnum serviceNameEnum : ServiceNameEnum.values()){
            if(code.equals(serviceNameEnum.getCode())) return serviceNameEnum;
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