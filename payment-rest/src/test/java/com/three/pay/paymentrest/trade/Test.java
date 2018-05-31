package com.three.pay.paymentrest.trade;

import com.alibaba.fastjson.JSONObject;
import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentcommon.po.MerOrderPo;
import com.three.pay.paymentcommon.po.MerPaySeqPo;
import com.three.pay.paymentcommon.utils.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:luiz
 * @Date: 2018/5/29 11:24
 * @Descripton:
 * @Modify :
 **/
public class Test {
    public static void main(String[] args) {
        CommonReqParam commonReqParam=new CommonReqParam();
        commonReqParam.setMerNo("6002111111119");
        commonReqParam.setCharsetCode("utf-8");
        commonReqParam.setNotifyUrl("www.baidu.com");

        commonReqParam.setServiceName("UNION_CREATE_ORDER");
        commonReqParam.setRequestTime(DateUtil.getDateTimeFormat(new Date()));
        commonReqParam.setSignType("RSA");
        commonReqParam.setSignVlaue("XXXXXXXXXXX");
        commonReqParam.setVersion("1.0");

        MerOrderPo merOrderPo=new MerOrderPo();
        merOrderPo.setProductNo("ALIPAY-SCAN_CODE");
        merOrderPo.setDiscountAmt("0.01");
        merOrderPo.setOrderAmt("0.02");
        merOrderPo.setPayAmt("0.01");
        merOrderPo.setEquipIp("192.168.1.1");
        merOrderPo.setEquipType("IOS");
        merOrderPo.setEquipNo("XXXXXXXXXXX001");
        merOrderPo.setGoodsName("小当家");
        merOrderPo.setUserNo("TH0001");
        MerPaySeqPo merPaySeqPo=new MerPaySeqPo();
        merPaySeqPo.setOrderNo("2018053000000000001");
        merPaySeqPo.setMerPaySeq("2018053000000000001");
        List<MerPaySeqPo> merPaySeqPoList=new ArrayList<MerPaySeqPo>();
        merPaySeqPoList.add(merPaySeqPo);
        merOrderPo.setOrderList(merPaySeqPoList);

        //merOrderPo.setGoodsList();
        merOrderPo.setResv1("001");
        merOrderPo.setResv2("002");
        merOrderPo.setResv3("003");
        commonReqParam.setReqContent(JSONObject.toJSONString(merOrderPo));
        System.err.println("commonReqParam:"+JSONObject.toJSONString(commonReqParam));
    }
}
