package com.three.pay.paymentweb.controller.channel;

import com.alibaba.fastjson.JSONObject;
import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentcommon.po.MerOrderPo;
import com.three.pay.paymentcommon.po.MerPaySeqPo;
import com.three.pay.paymentcommon.utils.DateUtil;
import com.three.pay.paymentcommon.utils.HttpClientUtil;
import com.three.pay.paymentweb.form.OrderForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:luiz
 * @Date: 2018/5/30 10:49
 * @Descripton:订单处理
 * @Modify :
 **/
@Controller
@RequestMapping("/channel")
public class OrderQueryController {
    private static final Logger logger= LoggerFactory.getLogger(OrderQueryController.class);
    private static final String payUrl="http://localhost:9002/api/trade";

    @RequestMapping(value = "/orderQuery",method = RequestMethod.POST)
    public ModelAndView createOrder(OrderForm orderForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
        ModelAndView modelAndView=new ModelAndView();
        CommonReqParam commonReqParam=new CommonReqParam();
        commonReqParam.setMerNo("6002111111119");
        commonReqParam.setCharsetCode("utf-8");
        // commonReqParam.setNotifyUrl("www.baidu.com");
        //commonReqParam.setProductNo("ALIPAY-SCAN_CODE");
        commonReqParam.setServiceName("QUERY_ORDER");
        commonReqParam.setRequestTime(DateUtil.getDateTimeFormat(new Date()));
        commonReqParam.setSignType("RSA2");
        commonReqParam.setSignVlaue("XXXXXXXXXXX");
        commonReqParam.setVersion("1.0");

        MerOrderPo merOrderPo=new MerOrderPo();
        MerPaySeqPo merPaySeqPo=new MerPaySeqPo();
        merPaySeqPo.setOrderNo(orderForm.getOrderNo());
        merPaySeqPo.setMerPaySeq(orderForm.getOrderNo());
        List<MerPaySeqPo> merPaySeqPoList=new ArrayList<MerPaySeqPo>();
        merPaySeqPoList.add(merPaySeqPo);
        merOrderPo.setOrderList(merPaySeqPoList);
        commonReqParam.setReqContent(JSONObject.toJSONString(merOrderPo));
        String respStr= HttpClientUtil.doPost(payUrl,JSONObject.toJSONString(commonReqParam));
        logger.info("[订单查询]返回数据:{}",respStr);
        JSONObject respJson=JSONObject.parseObject(respStr);
        String retCode=respJson.getString("retCode");
        String retMsg=respJson.getString("retMsg");
        String respData="";
        String viewName="channel/orderSuccess";
        if("200".equals(retCode)){
            respData=respJson.getString("data");
        }else{
             viewName="channel/error";
        }
        modelAndView.addObject("retMsg",retMsg);
        modelAndView.addObject("respData",respData);
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

}
