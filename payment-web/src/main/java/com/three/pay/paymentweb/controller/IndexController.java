package com.three.pay.paymentweb.controller;

import com.three.pay.paymentcommon.utils.IDUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author:luiz
 * @Date: 2018/5/24 13:32
 * @Descripton:模拟测试页面
 * @Modify :
 **/
@Controller
@RequestMapping("/web")
public class IndexController {

    @RequestMapping("/index")
    public ModelAndView index(ModelMap modelMap){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/createOrder")
    public ModelAndView createOrder(ModelMap modelMap){
        ModelAndView modelAndView=new ModelAndView();
        String orderNo= IDUtils.nextIdStr();
        modelMap.put("orderNo",orderNo);
        modelAndView.setViewName("createOrder");
        return modelAndView;
    }

    @RequestMapping("/orderList")
    public ModelAndView orderList(ModelMap modelMap){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("orderList");
        return modelAndView;
    }
}
