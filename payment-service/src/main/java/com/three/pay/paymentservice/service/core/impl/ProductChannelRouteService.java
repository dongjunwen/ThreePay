package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentcommon.enums.StatusEnum;
import com.three.pay.paymentjdbc.entity.ProductChannelRoute;
import com.three.pay.paymentjdbc.respository.ProductChannelRep;
import com.three.pay.paymentservice.service.core.IProductChannelRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:luiz
 * @Date: 2018/5/28 10:40
 * @Descripton:
 * @Modify :
 **/
@Service
public class ProductChannelRouteService implements IProductChannelRoute {
    @Autowired
    ProductChannelRep productChannelRep;
    @Override
    public List<ProductChannelRoute> findAvailable() {
        List<ProductChannelRoute> productChannelRoutes=productChannelRep.findAll();
        List<ProductChannelRoute> avalibleList=productChannelRoutes.stream().filter(s->s.getStatus().equals(StatusEnum.YES.getCode())).collect(Collectors.toList());
        return  avalibleList;
    }

    @Override
    public ProductChannelRoute findByMerNoAndPayWay(String merNo, String payWay) {
        return productChannelRep.findByMerNoAndPayWay(merNo,payWay);
    }
}
