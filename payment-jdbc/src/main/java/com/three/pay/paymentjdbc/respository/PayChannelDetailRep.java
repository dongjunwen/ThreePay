package com.three.pay.paymentjdbc.respository;

import com.three.pay.paymentjdbc.entity.PayChannelDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:luiz
 * @Date: 2018/5/24 17:20
 * @Descripton:
 * @Modify :
 **/
public interface PayChannelDetailRep extends JpaRepository<PayChannelDetail,Long> {

}
