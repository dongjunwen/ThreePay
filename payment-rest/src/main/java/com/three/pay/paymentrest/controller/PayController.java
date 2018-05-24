package com.three.pay.paymentrest.controller;

import com.three.pay.paymentapi.result.PayResult;
import com.three.pay.paymentcommon.vo.PayMerPayOrderVo;
import com.three.pay.paymentservice.service.IPayMerPayOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author:luiz
 * @Date: 2018/5/24 17:29
 * @Descripton:付款请求接口
 * @Modify :
 **/
@RestController
@RequestMapping("api/pay")
public class PayController {
    private static final Logger logger= LoggerFactory.getLogger(PayController.class);
    @Autowired
    IPayMerPayOrderService iPayMerPayOrderService;

    @RequestMapping("/createOrder")
    public PayResult<Integer> createOrder(@RequestBody PayMerPayOrderVo payMerPayOrderVo){
        logger.info("【三人行支付】收单开始...");
        PayResult<Integer> payResults=iPayMerPayOrderService.createOrder(payMerPayOrderVo);
        return payResults;
    }
}
