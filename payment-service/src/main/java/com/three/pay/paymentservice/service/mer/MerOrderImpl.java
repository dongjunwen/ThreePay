package com.three.pay.paymentservice.service.mer;

import com.alibaba.fastjson.JSON;
import com.three.pay.paymentapi.api.ITradeProcess;
import com.three.pay.paymentapi.enums.ServiceNameEnum;
import com.three.pay.paymentapi.result.PayResult;
import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentcommon.po.MerOrderPo;
import com.three.pay.paymentservice.utils.ValidatorUtil;

/**
 * @Author:luiz
 * @Date: 2018/5/25 16:38
 * @Descripton: 创建订单统一处理
 * @Modify :
 **/
public class MerOrderImpl implements ITradeProcess {

    @Override
    public boolean isSupport(ServiceNameEnum serviceNameEnum) {
        return ServiceNameEnum.UNION_CREATE_ORDER.equals(serviceNameEnum);
    }

    @Override
    public PayResult execute(CommonReqParam commonReqVo) {
        try{
            //1.参数校验
            String reqContent=commonReqVo.getReqContent();
            MerOrderPo merOrderPo= JSON.parseObject(reqContent, MerOrderPo.class);
            ValidatorUtil.validateEntity(merOrderPo);

            //3.路由 渠道层处理
            //4.更新表
            //5.返回结果
        }catch (Exception e){

        }

        return null;
    }
}
