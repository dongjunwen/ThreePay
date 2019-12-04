package com.three.pay.paymentweb.controller.channel;

import com.alibaba.fastjson.JSONObject;
import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentcommon.po.MerRefundOrderPo;
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
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * @Author:luiz
 * @Date: 2018/5/30 10:49
 * @Descripton:订单处理
 * @Modify :
 **/
@Controller
@RequestMapping("/channel")
public class RefundController {
    private static final Logger logger= LoggerFactory.getLogger(RefundController.class);
    //private static final String payUrl="http://111.231.141.23:9002/api/trade";
    private static final String payUrl="http://localhost:9005/api/trade";

    @RequestMapping(value = "/refundCreate",method = RequestMethod.POST)
    public ModelAndView createOrder(RefundForm refundForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
        ModelAndView modelAndView=new ModelAndView();
        CommonReqParam commonReqParam=new CommonReqParam();
        commonReqParam.setMerNo("6002111111119");
        commonReqParam.setCharsetCode("utf-8");
        commonReqParam.setServiceName("REFUND_ORDER");
        commonReqParam.setRequestTime(DateUtil.getDateTimeFormat(new Date()));
        commonReqParam.setSignType("MD5");
        commonReqParam.setVersion("1.0");

        MerRefundOrderPo merRefundOrderPo =new MerRefundOrderPo();
        merRefundOrderPo.setRefundNo(refundForm.getRefundNo());
        merRefundOrderPo.setMerOrderNo(refundForm.getMerPaySeq());
        merRefundOrderPo.setMerPaySeq(refundForm.getMerPaySeq());
        merRefundOrderPo.setRefundAmt(refundForm.getRefundAmt());
        merRefundOrderPo.setRefundDesc(refundForm.getRefundDesc());

        commonReqParam.setReqContent(JSONObject.toJSONString(merRefundOrderPo));
        String signStr= PairString.createLinkString(JSONObject.parseObject(JSONObject.toJSONString(commonReqParam)));
        String signValue=MD5Util.getMD5(signStr,"01234567890");
        commonReqParam.setSignValue(signValue);
        String respStr= HttpClientUtil.doPost(payUrl,JSONObject.toJSONString(commonReqParam));
        JSONObject respJson=JSONObject.parseObject(respStr);
        String retCode=respJson.getString("retCode");
        String retMsg=respJson.getString("retMsg");
        String respData="";
        String viewName="channel/orderSuccess";
        if("200".equals(retCode)){
            respData=respJson.getString("data");
            logger.info("[发起退款]返回数据:{}",respData);
            response.setContentType("text/html; charset=utf-8");
            OutputStream respOutStream= null;
            try {
                respOutStream = response.getOutputStream();
                respOutStream.write(respData.getBytes("utf-8"));
                respOutStream.flush();
                respOutStream.close();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                retMsg=e.getMessage();
                modelAndView.setViewName("error");
            }
        }else{
             viewName="channel/error";
        }
        modelAndView.addObject("retMsg",retMsg);
        modelAndView.addObject("respData",respData);
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

}
