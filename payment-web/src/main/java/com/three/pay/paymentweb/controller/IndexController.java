package com.three.pay.paymentweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author:luiz
 * @Date: 2018/5/24 13:32
 * @Descripton:官网首页
 * @Modify :
 **/
@Controller
@RequestMapping("")
public class IndexController {

    @RequestMapping("/web/index")
    public ModelAndView index(ModelMap modelMap){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("web/index");
        //modelAndView.setViewName("/web/index"); 这种方式打成jar包后报错
        return modelAndView;
    }


}
