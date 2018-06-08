package com.three.pay.paymentweb.controller.channel;

import com.three.pay.paymentcommon.utils.IDUtils;
import com.three.pay.paymentjdbc.entity.PayOrderDetail;
import com.three.pay.paymentservice.service.core.IPayOrderDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:luiz
 * @Date: 2018/5/24 13:32
 * @Descripton:模拟测试页面
 * @Modify :
 **/
@Controller
@RequestMapping("/channel")
public class ChannelController {
    private static final Logger logger= LoggerFactory.getLogger(ChannelController.class);

    @Autowired
    IPayOrderDetail iPayOrderDetail;

    @RequestMapping("/show")
    public ModelAndView index(ModelMap modelMap){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("channel/channelShow");
        return modelAndView;
    }

    @RequestMapping("/createOrder")
    public ModelAndView createOrder(ModelMap modelMap){
        ModelAndView modelAndView=new ModelAndView();
        String orderNo= IDUtils.nextIdStr();
        modelMap.put("orderNo",orderNo);
        modelAndView.setViewName("channel/createOrder");
        return modelAndView;
    }

    @RequestMapping("/orderQuery")
    public ModelAndView orderList(ModelMap modelMap){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("channel/orderQuery");
        return modelAndView;
    }

    @RequestMapping("/refundOrder")
    public ModelAndView refundOrder(ModelMap modelMap){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("channel/refundOrder");
        return modelAndView;
    }

    @RequestMapping("/refundQuery")
    public ModelAndView refundQuery(ModelMap modelMap){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("channel/refundQuery");
        return modelAndView;
    }

    @RequestMapping("/successForward")
    public ModelAndView successForward(HttpServletRequest request,ModelMap modelMap){
        //1.校验
            //todo 调用渠道层进行校验
        //2.根据订单号获取跳转地址
        String paySeqNo=request.getParameter("out_trade_no");
        String forwardUrl="http://www.baidu.com";
        if(paySeqNo!=null){
            PayOrderDetail payOrderDetail=iPayOrderDetail.findByUniqIndex(paySeqNo);
            forwardUrl=payOrderDetail.getForwardUrl();
        }
        logger.info("付款成功后支付单号{},跳转地址:{}",paySeqNo,forwardUrl);
        ModelAndView modelAndView=new ModelAndView();
        modelMap.put("forwardUrl",forwardUrl);
        modelAndView.setViewName("channel/successForward");
        return modelAndView;
    }
}
