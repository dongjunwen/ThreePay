package com.three.pay.paymentweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/createOrder")
    public String createOrder(){
        logger.info("【三人行支付】收单开始...");
        return "SUCCESS";
    }
}
