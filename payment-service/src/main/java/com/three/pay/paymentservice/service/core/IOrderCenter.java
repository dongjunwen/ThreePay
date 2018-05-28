package com.three.pay.paymentservice.service.core;

import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentcommon.dto.MerOrderDto;
import com.three.pay.paymentcommon.po.MerOrderPo; /**
 * @Author:luiz
 * @Date: 2018/5/28 15:18
 * @Descripton:
 * @Modify :
 **/
public interface IOrderCenter {
    void createOrder(MerOrderDto merOrderDto, CommonReqParam commonReqVo, MerOrderPo merOrderPo);
}
