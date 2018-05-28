package com.three.pay.paymentapi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Author:luiz
 * @Date: 2018/5/25 15:14
 * @Descripton:公共请求参数
 * @Modify :
 **/
@Getter
@Setter
@ToString
@ApiModel(value = "公共请求参数 CommonReqParam")
public class CommonReqParam {
    @NotBlank(message = "商户号不能为空")
    @Length(min = 1,max = 64,message = "商户号不能超过64")
    @ApiModelProperty(value = "商户号",required =true )
    private String merNo;
    @NotBlank(message = "服务名称不能为空")
    @Length(min = 1,max = 32,message = "商户号不能超过32")
    @ApiModelProperty(value = "服务名称",required =true )
    private String serviceName;
    @NotBlank(message = "字符编码不能为空")
    @Length(min = 1,max = 16,message = "字符编码不能超过16")
    @ApiModelProperty(value = "字符编码",required =true )
    private String charsetCode;
    @NotBlank(message = "签名类型不能为空")
    @Length(min = 1,max = 16,message = "签名类型不能超过16")
    @ApiModelProperty(value = "签名类型",required =true )
    private String signType;
    @NotBlank(message = "签名值不能为空")
    @Length(min = 1,max = 256,message = "签名值不能超过256")
    @ApiModelProperty(value = "签名值",required =true )
    private String signVlaue;
    @NotBlank(message = "请求时间不能为空")
    @Length(min = 19,max = 19,message = "请求时间长度为19")
    @ApiModelProperty(value = "请求时间",required =true )
    private String requestTime;
    @NotBlank(message = "版本号不能为空")
    @Length(min = 3,max = 8,message = "版本号不能超过8")
    @ApiModelProperty(value = "版本号",required =true )
    private String version;
    @Length(max = 256,message = "通知地址不能超过256")
    @ApiModelProperty(value = "通知地址",required =true )
    private String notifyUrl;
    @NotBlank(message = "产品编号不能为空")
    @Length(min = 1,max = 32,message = "产品编号不能超过32")
    @ApiModelProperty(value = "产品编号",required =true )
    private String productNo;
    @NotBlank(message = "详细内容不能为空")
    @ApiModelProperty(value = "详细内容",required =true )
    private String reqContent;
}
