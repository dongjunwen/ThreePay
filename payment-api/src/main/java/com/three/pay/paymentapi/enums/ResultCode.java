package com.three.pay.paymentapi.enums;



/**
 * Description:
 *
 * @author luiz
 * @version 1.0.0
 * @date: 2018-01-22 14:42
 */
public enum ResultCode {

    COMMON_PARAM_NULL("1001","参数为空"),
    COMMON_PARAM_INVALID("1002","参数不合法"),
    COMMON_DATA_EXISTS("1003", "数据已存在"),
    COMMON_DATA_NOT_EXISTS("1004", "数据不存在"),
    COMMON_QUERY_ERROR("1005", "数据执行SQL查询错误"),
    COMMON_RULE_ERROR("1006", "规则校验失败"),
    COMMON_DULIICATE_SUBMIT("1007", "重复提交"),
    COMMON_AUDIT_STAUS_ERROR("1008","不符合申请条件"),

    //基础资料 1100-1199
    USER_PASS_NOT_EQUAL("1100","两次密码不一致"),
    USER_OLD_PASS_ERROR("1101","原始密码错误"),



    SUCCESS("200","操作成功"),
    FAIL("500","系统异常"),
	USER_NO_LOGGED_IN("510","用户未登录"),
	FOR_UNAUTHORIZED("511","用户未授权"),
    USERNAME_OR_PASS_ERR("512","用户名或密码错误"), ;

    String code;
    String message;

    ResultCode(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    
    public static final ResultCode parse(String code){
    	for(ResultCode resultCode : ResultCode.values()){
    		if(code.equals(resultCode.getCode())) return resultCode;
    	}
    	return null;
    }
}
