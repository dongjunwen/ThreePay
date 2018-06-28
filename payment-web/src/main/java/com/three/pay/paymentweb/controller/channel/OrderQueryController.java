package com.three.pay.paymentweb.controller.channel;

import com.alibaba.fastjson.JSONObject;
import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentcommon.po.MerPaySeqPo;
import com.three.pay.paymentcommon.po.MerUnionOrderPo;
import com.three.pay.paymentcommon.utils.DateUtil;
import com.three.pay.paymentcommon.utils.HttpClientUtil;
import com.three.pay.paymentcommon.utils.MD5Util;
import com.three.pay.paymentcommon.utils.PairString;
import com.three.pay.paymentweb.form.OrderForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private static final String payUrl="http://localhost:9005/api/trade";

    @RequestMapping(value = "/orderQuery")
    public ModelAndView createOrder(OrderForm orderForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
        ModelAndView modelAndView=new ModelAndView();
        CommonReqParam commonReqParam=new CommonReqParam();
        commonReqParam.setMerNo("6002111111119");
        commonReqParam.setCharsetCode("utf-8");
        commonReqParam.setServiceName("QUERY_ORDER");
        commonReqParam.setRequestTime(DateUtil.getDateTimeFormat(new Date()));
        commonReqParam.setSignType("MD5");
        commonReqParam.setVersion("1.0");

        MerUnionOrderPo merUnionOrderPo =new MerUnionOrderPo();
        MerPaySeqPo merPaySeqPo=new MerPaySeqPo();
        merPaySeqPo.setMerOrderNo(orderForm.getMerOrderNo());
        merPaySeqPo.setMerPaySeq(orderForm.getMerOrderNo());
        List<MerPaySeqPo> merPaySeqPoList=new ArrayList<MerPaySeqPo>();
        merPaySeqPoList.add(merPaySeqPo);
        merUnionOrderPo.setOrderList(merPaySeqPoList);
        commonReqParam.setReqContent(JSONObject.toJSONString(merUnionOrderPo));
        String signStr= PairString.createLinkString(JSONObject.parseObject(JSONObject.toJSONString(commonReqParam)));
        String signValue= MD5Util.getMD5(signStr,"01234567890");
        commonReqParam.setSignValue(signValue);
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
