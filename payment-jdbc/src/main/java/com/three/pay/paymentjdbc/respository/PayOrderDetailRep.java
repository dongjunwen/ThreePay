package com.three.pay.paymentjdbc.respository;

import com.three.pay.paymentjdbc.entity.PayOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author:luiz
 * @Date: 2018/5/24 17:20
 * @Descripton:
 * @Modify :
 **/
public interface PayOrderDetailRep extends JpaRepository<PayOrderDetail,Long> {

    PayOrderDetail findByPaySeqNo(String paySeqNo);

    @Transactional
    @Modifying
    @Query("update PayOrderDetail u set u.payStatus = ?1," +
            "u.modiTime=?2," +
            "u.paySuccessTime=?3, " +
            "u.sellerAcct=?4," +
            "u.paylerAcct=?5, " +
            "u.respPayNo=?6, " +
            "u.merRecvAmt=?7, " +
            "u.thirdDiscountAmt=?8, " +
            "u.buyInvoiceAmt=?9, " +
            "u.buyPayAmt=?10 " +
            " where u.paySeqNo=?11" )
    void updateByPaySeqNo(long payStatus,
                          Timestamp modiTime,
                          Timestamp payTime,
                          String sellerAcct,
                          String paylerAcct,
                          String respPayNo,
                          BigDecimal merRecvAmt,
                          BigDecimal thirdDiscountAmt,
                          BigDecimal buyInvoiceAmt,
                          BigDecimal buyPayAmt,
                          String paySeqNo);

    @Query(value = "select * from pay_order_detail where trade_no=:tradeNo ",nativeQuery = true)
    List<PayOrderDetail> findByTradeNo(@Param("tradeNo") String tradeNo);
}
