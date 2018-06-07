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
    VALIDATE_ERROR("1006", "签名校验错误"),
    VALIDATE_TYPE_NOT_EXISTS("1007", "签名校验错误"),
    COMMON_DULIICATE_SUBMIT("1008", "重复提交"),

    //基础资料 1100-1199
    SERVICE_NOT_EXISTS("1100","服务不存在"),
    PRODUCT_NOT_VALID("1101","产品无效"),
    MER_NOT_VALID("1102","商户不合法"),
    MER_PRODUCT_NOT_VALID("1103","商户对应的产品无效"),
    PRODUCT_CHANNEL_NOT_EXISTS("1104","产品渠道不存在"),
    CHANNELDETAIL_NOT_VALID("1105","渠道详情无效"),
    CHANNEL_NOT_VALID("1106","渠道无效"),
    MER_NOT_EXISTS("1107","商户不存在"),
    PRODUCT_NOT_EXISTS("1108","产品不存在"),
    CHANNELDETAIL_NOT_EXISTS("1109","渠道详情不存在"),
    CHANNEL_NOT_EXISTS("1110","渠道不存在"),


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
