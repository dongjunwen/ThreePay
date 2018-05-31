package com.three.pay.paymentjdbc.respository;

import com.three.pay.paymentjdbc.entity.PayTradeTotal;
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
public interface PayTradeTotalRep extends JpaRepository<PayTradeTotal,Long> {
    @Transactional
    @Modifying
    @Query("update PayTradeTotal u set u.tradeStatus = ?1,u.modiTime=?2 where u.tradeNo = ?3")
    void updateByTradeNo(String tradeStatus,Timestamp modiTime,String tradeNo);
}
