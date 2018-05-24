package com.three.pay.paymentjdbc.respository;

import com.three.pay.paymentjdbc.entity.PayChannelDetail;
import com.three.pay.paymentjdbc.entity.PayMerChannel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:luiz
 * @Date: 2018/5/24 17:20
 * @Descripton:
 * @Modify :
 **/
public interface PayMerChannelRep extends JpaRepository<PayMerChannel,Long> {

}
