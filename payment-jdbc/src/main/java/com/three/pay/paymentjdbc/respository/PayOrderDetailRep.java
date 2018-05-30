package com.three.pay.paymentjdbc.respository;

import com.three.pay.paymentjdbc.entity.PayOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;

/**
 * @Author:luiz
 * @Date: 2018/5/24 17:20
 * @Descripton:
 * @Modify :
 **/
public interface PayOrderDetailRep extends JpaRepository<PayOrderDetail,Long> {

    PayOrderDetail findByPaySeqNo(String paySeqNo);

    @Modifying
    @Query("update PayOrderDetail u set u.payStatus = ?1,u.modiTime=?2,u.paySuccessTime=?3 where u.paySeqNo = ?4")
    void updateByPaySeqNo(long payStatus, Timestamp modiTime,Timestamp payTime,String paySeqNo);
}
