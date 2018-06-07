package com.three.pay.paymentservice.service.apiImpl.admin;

import com.three.pay.paymentapi.api.IPayOrderService;
import com.three.pay.paymentapi.result.PayResult;
import com.three.pay.paymentapi.vo.admin.PayOrderCondParam;
import com.three.pay.paymentapi.vo.admin.PayOrderResult;
import com.three.pay.paymentcommon.enums.PayStatusEnum;
import com.three.pay.paymentcommon.utils.DateUtil;
import com.three.pay.paymentjdbc.entity.MerOrder;
import com.three.pay.paymentservice.service.core.IMerOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<PayOrderResult> payOrderResultList=new ArrayList<PayOrderResult>();
        for(MerOrder merOrder:merOrderPage){
            PayOrderResult payOrderResult=new PayOrderResult();
            BeanUtils.copyProperties(merOrder,payOrderResult);
            payOrderResult.setPayStatusName(PayStatusEnum.parse((int)merOrder.getPayStatus()).getName());
            payOrderResult.setPayTime(DateUtil.getDateTimeFormat(merOrder.getPayTime()));
            payOrderResult.setCreateTime(DateUtil.getDateTimeFormat(merOrder.getCreateTime()));
            payOrderResult.setModiTime(DateUtil.getDateTimeFormat(merOrder.getModiTime()));
            payOrderResultList.add(payOrderResult);
        }
        Page payOrderResultPage=new PageImpl(payOrderResultList,merOrderPage.getPageable(),merOrderPage.getTotalElements());
        return PayResult.newSuccess(payOrderResultPage);
    }
}
