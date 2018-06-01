package com.three.pay.paymentweb.controller.channel;

import com.alibaba.fastjson.JSONObject;
import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentcommon.po.MerOrderPo;
import com.three.pay.paymentcommon.po.MerPaySeqPo;
import com.three.pay.paymentcommon.utils.DateUtil;
import com.three.pay.paymentcommon.utils.HttpClientUtil;
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
@RequestMapping("/web")
public class OrderController {
    private static final Logger logger= LoggerFactory.getLogger(OrderController.class);
    private static final String payUrl="http://111.231.141.23:9002/api/trade";

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
        commonReqParam.setSignType("RSA2");
        commonReqParam.setSignVlaue("XXXXXXXXXXX");
        commonReqParam.setVersion("1.0");

        MerOrderPo merOrderPo=new MerOrderPo();
        merOrderPo.setProductNo("ALIPAY-SCAN_CODE");
        merOrderPo.setDiscountAmt("0.00");
        merOrderPo.setOrderAmt(orderForm.getPayAmt());
        merOrderPo.setPayAmt(orderForm.getPayAmt());
        merOrderPo.setEquipIp(IpUtils.getIpAddr(request));
        merOrderPo.setEquipType("WEB");
        merOrderPo.setEquipNo("00000");
        merOrderPo.setGoodsName(orderForm.getGoodsName());
        merOrderPo.setUserNo("NONE");
        MerPaySeqPo merPaySeqPo=new MerPaySeqPo();
        merPaySeqPo.setOrderNo(orderForm.getOrderNo());
        merPaySeqPo.setMerPaySeq(orderForm.getOrderNo());
        List<MerPaySeqPo> merPaySeqPoList=new ArrayList<MerPaySeqPo>();
        merPaySeqPoList.add(merPaySeqPo);
        merOrderPo.setOrderList(merPaySeqPoList);
        //merOrderPo.setGoodsList();
        merOrderPo.setResv1("001");
        merOrderPo.setResv2("002");
        merOrderPo.setResv3("003");
        commonReqParam.setReqContent(JSONObject.toJSONString(merOrderPo));
        String respStr= HttpClientUtil.doPost(payUrl,JSONObject.toJSONString(commonReqParam));
        JSONObject respJson=JSONObject.parseObject(respStr);
        String retCode=respJson.getString("retCode");
        String retMsg=respJson.getString("retMsg");
        String respData="";
        String viewName="orderSuccess";
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
             viewName="error";
        }
        modelAndView.addObject("retMsg",retMsg);
        modelAndView.addObject("respData",respData);
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

}
