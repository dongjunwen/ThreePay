package com.three.pay.paymentservice.service.core.impl;

import com.three.pay.paymentapi.vo.admin.PayOrderCondParam;
import com.three.pay.paymentcommon.po.MerPaySeqPo;
import com.three.pay.paymentcommon.utils.DateUtil;
import com.three.pay.paymentjdbc.entity.MerOrder;
import com.three.pay.paymentjdbc.respository.MerOrderRepository;
import com.three.pay.paymentservice.service.core.IMerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @Author:luiz
 * @Date: 2018/5/24 17:48
 * @Descripton:商户收单
 * @Modify :
 **/
@Service
public class MerOrderService implements IMerOrder {
    @Autowired
    private MerOrderRepository merPayOrderRepository;

    @Override
    public Page<MerOrder> findPage(PayOrderCondParam payOrderCondParam) {
        Pageable pageable=new PageRequest(payOrderCondParam.getCurrPage(),payOrderCondParam.getPageSize());
        Specification<MerOrder> specification = new Specification<MerOrder>() {
                @Override
                public Predicate toPredicate(Root<MerOrder> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> list = new ArrayList<Predicate>();
                    if(null!=payOrderCondParam.getTradeBeginDate()&&!"".equals(payOrderCondParam.getTradeBeginDate())){
                        Date beginDate= DateUtil.formatDate(payOrderCondParam.getTradeBeginDate(),"yyyy-MM-dd HH:mm:ss");
                        list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("payTime").as(Date.class), beginDate));
                    }
                    if(null!=payOrderCondParam.getTradeEndDate()&&!"".equals(payOrderCondParam.getTradeEndDate())){
                        Date endDate= DateUtil.formatDate(payOrderCondParam.getTradeEndDate(),"yyyy-MM-dd HH:mm:ss");
                        list.add(criteriaBuilder.lessThan(root.get("payTime").as(Date.class),endDate));
                    }
                    if(null!=payOrderCondParam.getMerOrderNo()&&!"".equals(payOrderCondParam.getMerOrderNo())){
                        list.add(criteriaBuilder.equal(root.get("merOrderNo").as(String.class), payOrderCondParam.getMerOrderNo()));
                    }
                    if(null!=payOrderCondParam.getMerNo()&&!"".equals(payOrderCondParam.getMerNo())){
                        list.add(criteriaBuilder.equal(root.get("merNo").as(String.class), payOrderCondParam.getMerNo()));
                    }
                    if(null!=payOrderCondParam.getPayTradeNo()&&!"".equals(payOrderCondParam.getPayTradeNo())){
                        list.add(criteriaBuilder.equal(root.get("tradeNo").as(String.class), payOrderCondParam.getPayTradeNo()));
                    }
                    Predicate[] p = new Predicate[list.size()];
                    return criteriaBuilder.and(list.toArray(p));
                }
        };
        return  merPayOrderRepository.findAll(specification,pageable);
    }

    @Override
    public MerOrder queryOrder(MerPaySeqPo merPaySeqPo) {
        return merPayOrderRepository.findByMerOrderNoAndMerPaySeq(merPaySeqPo.getMerOrderNo(),merPaySeqPo.getMerPaySeq());
    }
}
