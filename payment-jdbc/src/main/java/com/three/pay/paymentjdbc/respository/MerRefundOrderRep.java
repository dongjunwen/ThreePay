package com.three.pay.paymentjdbc.respository;

import com.three.pay.paymentjdbc.entity.MerRefundOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author:luiz
 * @Date: 2018/5/24 17:20
 * @Descripton:
 * @Modify :
 **/
public interface MerRefundOrderRep extends JpaRepository<MerRefundOrder,Long> {

    List<MerRefundOrder> findByMerOrderNoAndMerPaySeq(String merOrderNo, String merPaySeq);

    @Transactional
    @Modifying
    @Query("update MerRefundOrder u set u.refundStatus = ?1,u.modiTime=?2 where u.tradeNo = ?3")
    void updateByTradeNo(long refundStatus, Timestamp modiTime, String tradeNo);
}
