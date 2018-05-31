package com.three.pay.paymentrest.trade;

import com.three.pay.paymentcommon.po.notify.NotifyPayParamPo;
import com.three.pay.paymentservice.service.core.IOrderCenter;
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
        iOrderCenter.notifyOrder(notifyPayParamPo);
    }
}
