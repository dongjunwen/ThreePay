package com.three.pay.paymentrest.controller;

import com.three.pay.paymentapi.api.ITradeProcess;
import com.three.pay.paymentapi.enums.ResultCode;
import com.three.pay.paymentapi.enums.ServiceNameEnum;
import com.three.pay.paymentapi.result.PayResult;
import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentrest.utils.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class TradeController {
    private static final Logger logger= LoggerFactory.getLogger(TradeController.class);
    @Autowired
    List<ITradeProcess> iTradeProcessList;

    @RequestMapping("/trade")
    public PayResult<Integer> createOrder(@RequestBody CommonReqParam commonReqVo){
        logger.info("【三人行支付】交易统一入口,请求参数:{}",commonReqVo);
        PayResult payResult=PayResult.newSuccess();
        try {
            //1.参数统一校验
            ValidatorUtil.validateEntity(commonReqVo);
            //todo 参数数据校验

            if(iTradeProcessList==null || iTradeProcessList.size()==0){
                payResult.setErrorCode(ResultCode.SERVICE_NOT_EXISTS);
            }
            //2.查找对应的服务类进行处理
            String serviceName=commonReqVo.getServiceName();
            ITradeProcess tradeProcessImpl=null;
            for(ITradeProcess iTradeProcess:iTradeProcessList){
                if(iTradeProcess.isSupport(ServiceNameEnum.parse(serviceName))){
                    tradeProcessImpl=iTradeProcess;
                }
            }
            if(tradeProcessImpl==null){
                payResult.setErrorCode(ResultCode.SERVICE_NOT_EXISTS);
            }
            //3.返回结果
            payResult=tradeProcessImpl.execute(commonReqVo);
            logger.info("【三人行支付】交易统一入口,处理结果:{},请求参数:{}",payResult,commonReqVo);
        }catch(Exception e){
            logger.error("【三人行支付】交易统一入口,请求参数:{}发生异常:{}",commonReqVo,e);
            payResult.setErrorCode(ResultCode.FAIL);
        }
        return payResult;
    }


}
