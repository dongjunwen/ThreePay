package com.three.pay.paymentweb.controller.admin;

import com.three.pay.paymentapi.api.IPayOrderService;
import com.three.pay.paymentapi.enums.ResultCode;
import com.three.pay.paymentapi.result.PayResult;
import com.three.pay.paymentapi.vo.admin.PayOrderCondParam;
import com.three.pay.paymentweb.utils.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:luiz
 * @Date: 2018/6/4 10:45
 * @Descripton:支付订单查询
 * @Modify :
 **/
@RestController
@RequestMapping("/admin/order")
@Api(tags = "支付订单",description = "支付订单相关API")
public class AdminOrderQueryController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IPayOrderService iPayOrderService;

    @ApiOperation(value = "支付订单查询")
    @RequestMapping(value = "findPage",method = RequestMethod.GET)
    public PayResult findPage(PayOrderCondParam payOrderCondParam){
        PayResult payResult=PayResult.newSuccess();
        try{
            ValidatorUtil.validateEntity(payOrderCondParam);
            payResult=iPayOrderService.findOrderPage(payOrderCondParam);
        }catch (Exception e){
            logger.error("[支付订单查询]发生异常:{}",e);
            payResult.setError(ResultCode.FAIL.getCode(),e.getMessage());
        }
        return payResult;
    }

}
