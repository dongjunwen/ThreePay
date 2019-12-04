package com.three.pay.paymentweb.controller.channel;

import com.alibaba.fastjson.JSONObject;
import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentcommon.po.MerRefundQueryPo;
import com.three.pay.paymentcommon.utils.DateUtil;
import com.three.pay.paymentcommon.utils.HttpClientUtil;
import com.three.pay.paymentcommon.utils.MD5Util;
import com.three.pay.paymentcommon.utils.PairString;
import com.three.pay.paymentweb.form.RefundForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author:luiz
 * @Date: 2018/5/30 10:49
 * @Descripton:订单处理
 * @Modify :
 **/
@Controller
@RequestMapping("/channel")
public class RefundQueryController {
    private static final Logger logger= LoggerFactory.getLogger(RefundQueryController.class);
    private static final String payUrl="http://localhost:9005/api/trade";

    @RequestMapping(value = "/refundQuery",method = RequestMethod.POST)
    public ModelAndView createOrder(RefundForm refundForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
        ModelAndView modelAndView=new ModelAndView();
        CommonReqParam commonReqParam=new CommonReqParam();
        commonReqParam.setMerNo("6002111111119");
        commonReqParam.setCharsetCode("utf-8");
        commonReqParam.setServiceName("QUERY_REFUND");
        commonReqParam.setRequestTime(DateUtil.getDateTimeFormat(new Date()));
        commonReqParam.setSignType("MD5");
        commonReqParam.setVersion("1.0");

        MerRefundQueryPo merRefundQueryPo=new MerRefundQueryPo();
        merRefundQueryPo.setRefundNo(refundForm.getRefundNo());
        commonReqParam.setReqContent(JSONObject.toJSONString(merRefundQueryPo));
        String signStr= PairString.createLinkString(JSONObject.parseObject(JSONObject.toJSONString(commonReqParam)));
        String signValue= MD5Util.getMD5(signStr,"01234567890");
        commonReqParam.setSignValue(signValue);
        logger.info("[退款查询]请求参数:{}",commonReqParam);
        String respStr= HttpClientUtil.doPost(payUrl,JSONObject.toJSONString(commonReqParam));
        logger.info("[退款查询]返回数据:{}",respStr);
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
