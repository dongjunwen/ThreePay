package com.three.pay.paymentchannel.alipay;

import com.alibaba.fastjson.JSONObject;
import com.three.pay.paymentchannel.IChannelCore;
import com.three.pay.paymentchannel.param.ChannelReqParam;
import com.three.pay.paymentchannel.param.ChannelRespParam;
import com.three.pay.paymentcommon.dto.MerOrderDto;
import com.three.pay.paymentcommon.enums.PayWayEnum;
import com.three.pay.paymentcommon.utils.PairString;
import com.three.pay.paymentcommon.utils.RSAUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:luiz
 * @Date: 2018/5/28 11:16
 * @Descripton:
 * @Modify :
 **/
@Service
public class AliPayTradePay implements IChannelCore {
    private static final Logger logger= LoggerFactory.getLogger(AliPayTradePay.class);

    private static final String alipayUrl="https://openapi.alipaydev.com/gateway.do";


    @Override
    public boolean isSupport(PayWayEnum payWayEnum) {
        return PayWayEnum.ALIPAY_SCAN_CODE.equals(payWayEnum);
    }

    @Override
    public ChannelRespParam channelProcess(MerOrderDto merOrderDto, ChannelReqParam channelReqParam) {
        //1.构造请求参数
        String reqContent=buildReqParam(merOrderDto,channelReqParam);
        logger.info("[支付宝交易]返回内容:{}",reqContent);
        //2.发送请求
        //String respContent= HttpClientUtil.doPost(alipayUrl,reqContent);
        ChannelRespParam channelRespParam=new ChannelRespParam();
        channelRespParam.setRespCode("00");
        channelRespParam.setRespMsg("成功");
        channelRespParam.setRespContent(reqContent);
        //3.处理响应
        return channelRespParam;
    }

    private String buildReqParam(MerOrderDto merOrderDto, ChannelReqParam channelReqParam) {
        Map reqParam=new ConcurrentHashMap<>();
        reqParam.put("app_id",merOrderDto.getChannelPartenerNo());
        reqParam.put("method","alipay.trade.page.pay");
        reqParam.put("format","json");
        reqParam.put("charset","utf-8");
        reqParam.put("timestamp",channelReqParam.getRequestTime());
        reqParam.put("version","1.0");
        reqParam.put("notify_url","http://111.231.141.23:9002/AlipayNotify/pagePay");
        reqParam.put("return_url","http://111.231.141.23:9001/web/index");
        reqParam.put("sign_type",merOrderDto.getSignType());
        //jsonObject.put("app_auth_token","1.0");

        JSONObject bizContent=new JSONObject();
        bizContent.put("out_trade_no",merOrderDto.getInnerSeqNo());
        bizContent.put("product_code","FAST_INSTANT_TRADE_PAY");
        JSONObject reqContent=JSONObject.parseObject(channelReqParam.getReqContent());
        bizContent.put("subject",reqContent.getString("goodsName"));
        bizContent.put("total_amount",reqContent.getString("payAmt"));
        bizContent.put("body",reqContent.getString("goodsName"));
        reqParam.put("biz_content",bizContent.toString());
        String content=  PairString.createLinkString(reqParam);
        String signValue=RSAUtils.signWithRsa2(content,merOrderDto.getPrivKey());
        reqParam.put("sign",signValue);

        reqParam.remove("biz_content");
        String baseUrl=PairString.createLinkStringForEncode(reqParam);
        baseUrl=alipayUrl+"?"+baseUrl;
        //转义后给前端显示
       // String parameterContent=StringHtmlUtils.htmlEncode(bizContent.toString());
        return buildForm(baseUrl,bizContent);
    }


    public static String buildForm(String baseUrl, JSONObject bizContent) {
        java.lang.StringBuffer sb = new StringBuffer();
        sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
        sb.append("<title>付款</title>");
        sb.append("</head>");
        sb.append("<form name=\"punchout_form\" method=\"post\" action=\"");
        sb.append(baseUrl);
        sb.append("\">\n");
        sb.append(buildHiddenField(bizContent));
       // sb.append("<input type=\"submit\" value=\"立即支付\"  >\n");
        sb.append("<input type=\"submit\" value=\"立即支付\" style=\"display:none\" >\n");
        sb.append("</form>\n");
        sb.append("<script>document.forms[0].submit();</script>");
        sb.append("</html>");
        java.lang.String form = sb.toString();
        return form;
    }

    private static String buildHiddenField( JSONObject bizContent) {
        java.lang.StringBuffer sb = new StringBuffer();
        sb.append("<input type=\"hidden\" name=\"");
        sb.append("biz_content");

        sb.append("\" value=\"");
        //转义双引号
       String a = bizContent.toString().replace("\"", "&quot;");
        sb.append(a).append("\">\n");
        return sb.toString();
    }



}
