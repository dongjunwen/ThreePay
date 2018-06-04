package com.three.pay.paymentservice.service.core;

import com.three.pay.paymentapi.vo.admin.PayOrderCondParam;
import com.three.pay.paymentjdbc.entity.MerOrder;
import org.springframework.data.domain.Page;

/**
 * @Author:luiz
 * @Date: 2018/5/24 17:27
 * @Descripton:
 * @Modify :
 **/
public interface IMerOrder {

    Page<MerOrder> findPage(PayOrderCondParam payOrderCondParam);
}
