package com.three.pay.paymentrest.trade;

import com.alibaba.fastjson.JSON;
import com.three.pay.paymentcommon.po.notify.NotifyPayParamPo;
import com.three.pay.paymentservice.service.core.IOrderCenter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Author:luiz
 * @Date: 2018/5/30 20:27
 * @Descripton:
 * @Modify :
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderCenterTest {
    @Autowired
    IOrderCenter iOrderCenter;
    @Test
    public void orderNotify(){
        NotifyPayParamPo notifyPayParamPo=new NotifyPayParamPo();
        notifyPayParamPo.setPaySeqNo("S1001750103561601024");
        notifyPayParamPo.setThirdNotifyContent("SSSSSS");
        notifyPayParamPo.setTradeStatus(1);
        notifyPayParamPo.setSellerId("@@@@@@");
        notifyPayParamPo.setBuyllerId("11111");
        notifyPayParamPo.setThirdCreateTime(new Date());
        notifyPayParamPo.setThirdPayTime(new Date());
        notifyPayParamPo.setThirdTradeNo("9999999998");
        log.info(JSON.toJSONString(notifyPayParamPo));
        //iOrderCenter.notifyOrder(notifyPayParamPo);
    }
}
