package com.three.pay.paymentrest.controller;

import com.alibaba.fastjson.JSONObject;
import com.three.pay.paymentapi.api.ITradeProcess;
import com.three.pay.paymentapi.enums.ResultCode;
import com.three.pay.paymentapi.enums.ServiceNameEnum;
import com.three.pay.paymentapi.except.BusinessException;
import com.three.pay.paymentapi.result.PayResult;
import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentcommon.enums.SignTypeEnum;
import com.three.pay.paymentcommon.utils.MD5Util;
import com.three.pay.paymentcommon.utils.PairString;
import com.three.pay.paymentcommon.utils.RSAUtils;
import com.three.pay.paymentjdbc.entity.MerInfo;
import com.three.pay.paymentrest.utils.ValidatorUtil;
import com.three.pay.paymentservice.service.core.IMerInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:luiz
 * @Date: 2018/5/24 17:29
 * @Descripton:商户统一交易入口
 * @Modify :
 **/
@RestController
@RequestMapping("/api")
@Api(tags = "全部交易接口",description = "交易接口相关API")
public class TradeController {
    private static final Logger logger= LoggerFactory.getLogger(TradeController.class);
    @Autowired
    List<ITradeProcess> iTradeProcessList;
    @Autowired
    IMerInfo iMerInfo;

    @RequestMapping(value = "/trade",method = RequestMethod.POST)
    @ApiOperation(value="交易", notes="交易")
    @ApiParam(name = "commonReqVo", value = "交易请求参数 commonReqVo", required = true)
    public PayResult<Integer> createOrder(@RequestBody CommonReqParam commonReqVo){
        logger.info("【三人行支付】交易统一入口,请求参数:{}",commonReqVo);
        PayResult payResult=PayResult.newSuccess();
        try {
            //1.参数统一校验
            ValidatorUtil.validateEntity(commonReqVo);
            //2.签名校验
            validData(commonReqVo);
            ITradeProcess tradeProcessImpl=findServiceImpl(commonReqVo.getServiceName());
            //3.返回结果
            payResult=tradeProcessImpl.execute(commonReqVo);
            logger.info("【三人行支付】交易统一入口,处理结果:{},请求参数:{}",payResult,commonReqVo);
        }catch (BusinessException be){
            logger.error("【三人行支付】交易统一入口,请求参数:{}发生异常:{}",commonReqVo,be);
            payResult.setError(be.getCode(),be.getMsg());
        }catch(Exception e){
            logger.error("【三人行支付】交易统一入口,请求参数:{}发生异常:{}",commonReqVo,e);
            payResult.setErrorCode(ResultCode.FAIL);
        }
        return payResult;
    }

    /**
     * 按照服务名称 查找服务实现类
     * @param serviceName
     * @return
     */
    private ITradeProcess findServiceImpl(String serviceName) {
        if(iTradeProcessList==null || iTradeProcessList.size()==0){
            throw new BusinessException(ResultCode.SERVICE_NOT_EXISTS);
        }
        //2.查找对应的服务类进行处理
        ITradeProcess tradeProcessImpl=null;
        for(ITradeProcess iTradeProcess:iTradeProcessList){
            if(iTradeProcess.isSupport(ServiceNameEnum.parse(serviceName))){
                tradeProcessImpl=iTradeProcess;
            }
        }
        if(tradeProcessImpl==null){
            throw new BusinessException(ResultCode.SERVICE_NOT_EXISTS);
        }
        return tradeProcessImpl;
    }

    /**
     * 数据验证及签名校验
     * @param commonReqVo
     */
    private void validData(CommonReqParam commonReqVo) {
        JSONObject reqParam=JSONObject.parseObject(JSONObject.toJSONString(commonReqVo));
        String reqSignValue= reqParam.getString("signValue");
        reqParam.remove("signValue");
        String signStr=PairString.createLinkString(reqParam);
        String signType=commonReqVo.getSignType();
        SignTypeEnum signTypeEnum=SignTypeEnum.parse(signType);
        if(signTypeEnum==null){
            throw new BusinessException(ResultCode.VALIDATE_TYPE_NOT_EXISTS);
        }
        String merNo=commonReqVo.getMerNo();
        MerInfo merInfo=iMerInfo.findByMerNo(merNo);
        if(merInfo==null || StringUtils.isEmpty(merInfo.getValidKey())){
            throw new BusinessException(ResultCode.MER_NOT_VALID);
        }
        String validKey=merInfo.getValidKey();//获取验证签名的密钥
        if(SignTypeEnum.MD5.equals(signTypeEnum)){
            String signValue=MD5Util.getMD5(signStr,validKey);
            if(!reqSignValue.equals(signValue)){
                throw new BusinessException(ResultCode.VALIDATE_ERROR);
            }
        }else if(SignTypeEnum.RSA.equals(signTypeEnum)){
            boolean validFlag=RSAUtils.verifyWithRsa2(signStr,reqSignValue,validKey);
            if(!validFlag){
                throw new BusinessException(ResultCode.VALIDATE_ERROR);
            }
        }

    }


}
