package com.three.pay.paymentapi.except;

import com.three.pay.paymentapi.enums.ResultCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author:luiz
 * @Date: 2018/1/22 15:34
 * @Descripton:业务异常类
 * @Modify :
 **/
@Getter
@Setter
public class BusinessException extends RuntimeException {
    private String code;
    private String msg;
    public BusinessException(String msg){
        super(msg);
        this.msg=msg;
    }
    public BusinessException(String code, String msg){
        super(msg);
        this.code=code;
        this.msg=msg;
    }

    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code=resultCode.getCode();
        this.msg=resultCode.getMessage();
    }
}
