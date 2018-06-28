package com.three.pay.paymentjdbc.respository;

import com.three.pay.paymentjdbc.entity.MerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author:luiz
 * @Date: 2018/5/24 17:20
 * @Descripton:
 * @Modify :
 **/
@Repository
public interface MerOrderRepository extends JpaRepository<MerOrder,Long>,JpaSpecificationExecutor<MerOrder> {

    MerOrder findByMerOrderNoAndMerPaySeq( String merOrderNo, String merPaySeq);
}
