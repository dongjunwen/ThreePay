package com.three.pay.paymentservice.service.channel;

import com.three.pay.paymentapi.vo.CommonReqParam;
import com.three.pay.paymentchannel.IChannelCore;
import com.three.pay.paymentchannel.param.ChannelReqParam;
import com.three.pay.paymentchannel.param.ChannelRespParam;
import com.three.pay.paymentcommon.dto.MerChannelInfo;
import com.three.pay.paymentcommon.enums.ChannelActionEnum;
import com.three.pay.paymentcommon.enums.PayWayEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:luiz
 * @Date: 2018/5/28 11:19
 * @Descripton:渠道层统一处理
 * @Modify :
 **/
@Service
public  class ChannelService implements IChannelService {
    private static final Logger logger= LoggerFactory.getLogger(ChannelService.class);
    @Autowired
    List<IChannelCore> iChannelCores;

    private IChannelCore getByPayWay(String payWay,String channelAction) {
        return iChannelCores.stream().filter(s->s.isSupport(PayWayEnum.parse(payWay), ChannelActionEnum.parse(channelAction))).findFirst().get();
    }

    @Override
    public ChannelRespParam channelProcess(MerChannelInfo merChannelInfo, CommonReqParam commonReqVo) {
        IChannelCore iChannelCore=getByPayWay(merChannelInfo.getPayWay(), merChannelInfo.getChannelAction());
        ChannelReqParam channelReqParam=new ChannelReqParam();
        BeanUtils.copyProperties(commonReqVo,channelReqParam);
        logger.info("[渠道层]请求第三方参数:{}",channelReqParam);
        ChannelRespParam channelRespParam=iChannelCore.channelProcess(merChannelInfo,channelReqParam);
        logger.info("[渠道层]请求第三方响应:{}",channelRespParam);
        return channelRespParam;
    }
}
