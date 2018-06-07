package com.three.pay.paymentweb.controller.channel;

import com.alibaba.fastjson.JSONObject;
import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentcommon.po.MerOrderPo;
import com.three.pay.paymentcommon.po.MerPaySeqPo;
import com.three.pay.paymentcommon.utils.DateUtil;
import com.three.pay.paymentcommon.utils.HttpClientUtil;
import com.three.pay.paymentcommon.utils.MD5Util;
import com.three.pay.paymentcommon.utils.PairString;
import com.three.pay.paymentweb.form.OrderForm;
import com.three.pay.paymentweb.utils.IpUtils;
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
public class OrderController {
    private static final Logger logger= LoggerFactory.getLogger(OrderController.class);
    //private static final String payUrl="http://111.231.141.23:9002/api/trade";
    private static final String payUrl="http://localhost:9005/api/trade";

    @RequestMapping(value = "/submitOrder",method = RequestMethod.POST)
    public ModelAndView createOrder(OrderForm orderForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
        ModelAndView modelAndView=new ModelAndView();
        CommonReqParam commonReqParam=new CommonReqParam();
        commonReqParam.setMerNo("6002111111119");
        commonReqParam.setCharsetCode("utf-8");
        commonReqParam.setForwardUrl("http://www.baidu.com");
        commonReqParam.setNotifyUrl("http://www.baidu.com");
        commonReqParam.setServiceName("UNION_CREATE_ORDER");
        commonReqParam.setRequestTime(DateUtil.getDateTimeFormat(new Date()));
        commonReqParam.setSignType("MD5");
        commonReqParam.setVersion("1.0");

        MerOrderPo merOrderPo=new MerOrderPo();
        merOrderPo.setProductNo("ALIPAY-SCAN_CODE");
        merOrderPo.setDiscountAmt("0.00");
        merOrderPo.setOrderAmt(orderForm.getPayAmt());
        merOrderPo.setPayAmt(orderForm.getPayAmt());
        merOrderPo.setEquipIp(IpUtils.getIpAddr(request));
        merOrderPo.setEquipType("WEB");
        merOrderPo.setGoodsName(orderForm.getGoodsName());
        merOrderPo.setUserNo("NONE");
        MerPaySeqPo merPaySeqPo=new MerPaySeqPo();
        merPaySeqPo.setOrderNo(orderForm.getOrderNo());
        merPaySeqPo.setMerPaySeq(orderForm.getOrderNo());
        List<MerPaySeqPo> merPaySeqPoList=new ArrayList<MerPaySeqPo>();
        merPaySeqPoList.add(merPaySeqPo);
        merOrderPo.setOrderList(merPaySeqPoList);


        commonReqParam.setReqContent(JSONObject.toJSONString(merOrderPo));
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
            logger.info("[订单创建]返回数据:{}",respData);
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
