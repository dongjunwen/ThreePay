package com.three.pay.paymentservice.service.apiImpl.admin;

import com.three.pay.paymentapi.api.IPayOrderService;
import com.three.pay.paymentapi.result.PayResult;
import com.three.pay.paymentapi.vo.admin.PayOrderCondParam;
import com.three.pay.paymentjdbc.entity.MerOrder;
import com.three.pay.paymentservice.service.core.IMerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @Author:luiz
 * @Date: 2018/6/4 11:10
 * @Descripton:后台管理系统 支付订单实现类
 * @Modify :
 **/
@Service
public class PayOrderServiceImpl implements IPayOrderService {
    @Autowired
    IMerOrder iMerOrder;

    @Override
    public PayResult findOrderPage(PayOrderCondParam payOrderCondParam) {
        Page<MerOrder> merOrderPage= iMerOrder.findPage(payOrderCondParam);
        return PayResult.newSuccess(merOrderPage);
    }
}
