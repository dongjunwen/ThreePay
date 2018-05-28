package com.three.pay.paymentchannel.alipay;

import com.alibaba.fastjson.JSONObject;
import com.three.pay.paymentchannel.IChannelCore;
import com.three.pay.paymentchannel.param.ChannelReqParam;
import com.three.pay.paymentchannel.param.ChannelRespParam;
import com.three.pay.paymentchannel.utils.PoolingHttpClientService;
import com.three.pay.paymentcommon.dto.MerOrderDto;
import com.three.pay.paymentcommon.enums.PayWayEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:luiz
 * @Date: 2018/5/28 11:16
 * @Descripton:
 * @Modify :
 **/
@Service
public class AliPayTradePay implements IChannelCore {

    @Autowired
    PoolingHttpClientService httpClientService;

    @Override
    public boolean isSupport(PayWayEnum payWayEnum) {
        return PayWayEnum.ALIPAY_SCAN_CODE.equals(payWayEnum);
    }

    @Override
    public ChannelRespParam channelProcess(MerOrderDto merOrderDto, ChannelReqParam channelReqParam) {
        //1.构造请求参数
        String reqContent=buildReqParam(merOrderDto,channelReqParam);
        //2.发送请求
        httpClientService.postXml("https://openapi.alipaydev.com/gateway.do",reqContent,"utf-8");
        //3.处理响应
        return null;
    }

    private String buildReqParam(MerOrderDto merOrderDto, ChannelReqParam channelReqParam) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("app_id",AlipayConstant.appId);
        jsonObject.put("method","alipay.trade.pay");
        jsonObject.put("format","JSON");
        jsonObject.put("charset","utf-8");
        jsonObject.put("sign_type","RSA2");
        //TODO
        jsonObject.put("sign","22222222");
        jsonObject.put("timestamp",channelReqParam.getRequestTime());
        jsonObject.put("version","1.0");
        jsonObject.put("notify_url","1.0");
        jsonObject.put("app_auth_token","1.0");

        JSONObject bizContent=new JSONObject();
        bizContent.put("out_trade_no",merOrderDto.getInnerSeqNo());
        bizContent.put("scene","bar_code");
        bizContent.put("auth_code",AlipayConstant.authToken);
        bizContent.put("product_code","FACE_TO_FACE_PAYMENT");
        JSONObject reqContent=JSONObject.parseObject(channelReqParam.getReqContent());
        bizContent.put("subject",reqContent.getString("goodsName"));
        bizContent.put("total_amount",reqContent.getString("payAmt"));
        bizContent.put("discountable_amount",reqContent.getString("discountAmt"));
        bizContent.put("body",reqContent.getString("goodsName"));
        //bizContent.put("buyer_id","1.0");
        //bizContent.put("seller_id","1.0");
        //  bizContent.put("trans_currency","1.0");
        //  bizContent.put("settle_currency","1.0");
        jsonObject.put("biz_content",bizContent);
        return jsonObject.toString();
    }
}
