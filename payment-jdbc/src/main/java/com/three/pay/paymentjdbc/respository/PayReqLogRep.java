package com.three.pay.paymentjdbc.respository;

import com.three.pay.paymentjdbc.entity.PayReqLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:luiz
 * @Date: 2018/5/24 17:20
 * @Descripton:
 * @Modify :
 **/
public interface PayReqLogRep extends JpaRepository<PayReqLog,Long> {

}
