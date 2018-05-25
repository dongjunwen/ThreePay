package com.three.pay.paymentjdbc.respository;

import com.three.pay.paymentjdbc.entity.ChannelInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:luiz
 * @Date: 2018/5/24 17:20
 * @Descripton:
 * @Modify :
 **/
public interface PayChannelInfoRep extends JpaRepository<ChannelInfo,Long> {

}
