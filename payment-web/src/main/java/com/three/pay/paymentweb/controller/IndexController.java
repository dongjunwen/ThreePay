package com.three.pay.paymentweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:luiz
 * @Date: 2018/5/24 13:32
 * @Descripton:测试接口
 * @Modify :
 **/
@RestController
@RequestMapping("")
public class IndexController {

    @RequestMapping("index")
    public String test(){
        return "00";
    }
}
