package com.three.pay.paymentjdbc.respository;

import com.three.pay.paymentjdbc.entity.MerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * @Author:luiz
 * @Date: 2018/5/24 17:20
 * @Descripton:
 * @Modify :
 **/
public interface MerOrderRep extends JpaRepository<MerOrder,Long> {
    @Transactional
    @Modifying
    @Query("update MerOrder u set u.payStatus = ?1,u.modiTime=?2 where u.tradeNo = ?3")
    void updateByTradeNo(long payStatus, Timestamp modiTime,String tradeNo);
}
