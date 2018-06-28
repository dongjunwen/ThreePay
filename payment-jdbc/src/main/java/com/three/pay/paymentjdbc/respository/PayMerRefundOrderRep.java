package com.three.pay.paymentjdbc.respository;

import com.three.pay.paymentjdbc.entity.MerRefundOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author:luiz
 * @Date: 2018/5/24 17:20
 * @Descripton:
 * @Modify :
 **/
@Repository
public interface PayMerRefundOrderRep extends JpaRepository<MerRefundOrder,Long> {

}
