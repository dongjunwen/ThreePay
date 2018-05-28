package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentjdbc.entity.ProductInfo;
import com.three.pay.paymentjdbc.respository.ProductInfoRep;
import com.three.pay.paymentservice.service.core.IProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:luiz
 * @Date: 2018/5/28 10:10
 * @Descripton:
 * @Modify :
 **/
@Service
public class ProductInfoService implements IProductInfo {
    @Autowired
    ProductInfoRep productInfoRep;
    @Override
    public ProductInfo findByProductNo(String productNo) {
        return productInfoRep.findByProdctNo(productNo);
    }
}
