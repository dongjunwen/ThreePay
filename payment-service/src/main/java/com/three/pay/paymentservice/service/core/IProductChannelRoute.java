package com.three.pay.paymentservice.service.core;

import com.three.pay.paymentjdbc.entity.ProductChannelRoute;

import java.util.List;

/**
 * @Author:luiz
 * @Date: 2018/5/28 10:04
 * @Descripton:
 * @Modify :
 **/
public interface IProductChannelRoute {
    List<ProductChannelRoute> findAvailable();

    ProductChannelRoute findByMerNoAndPayWay(String merNo, String payWay);

    List<ProductChannelRoute> findByMerNoAndProductNo(String merNo, String productNo);
}
