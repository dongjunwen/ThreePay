package com.three.pay.paymentrest.trade;

import com.alibaba.fastjson.JSONObject;
import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentcommon.po.MerUnionOrderPo;
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
        commonReqParam.setSignValue("XXXXXXXXXXX");
        commonReqParam.setVersion("1.0");

        MerUnionOrderPo merUnionOrderPo =new MerUnionOrderPo();
        merUnionOrderPo.setProductNo("ALIPAY-SCAN_CODE");
        merUnionOrderPo.setDiscountAmt("0.01");
        merUnionOrderPo.setOrderAmt("0.02");
        merUnionOrderPo.setPayAmt("0.01");
        merUnionOrderPo.setEquipIp("192.168.1.1");
        merUnionOrderPo.setEquipType("IOS");
        merUnionOrderPo.setEquipNo("XXXXXXXXXXX001");
        merUnionOrderPo.setGoodsName("小当家");
        merUnionOrderPo.setUserNo("TH0001");
        MerPaySeqPo merPaySeqPo=new MerPaySeqPo();
        merPaySeqPo.setMerOrderNo("2018053000000000001");
        merPaySeqPo.setMerPaySeq("2018053000000000001");
        List<MerPaySeqPo> merPaySeqPoList=new ArrayList<MerPaySeqPo>();
        merPaySeqPoList.add(merPaySeqPo);
        merUnionOrderPo.setOrderList(merPaySeqPoList);

        //merUnionOrderPo.setGoodsList();
        merUnionOrderPo.setResv1("001");
        merUnionOrderPo.setResv2("002");
        merUnionOrderPo.setResv3("003");
        commonReqParam.setReqContent(JSONObject.toJSONString(merUnionOrderPo));
        System.err.println("commonReqParam:"+JSONObject.toJSONString(commonReqParam));
    }
}
