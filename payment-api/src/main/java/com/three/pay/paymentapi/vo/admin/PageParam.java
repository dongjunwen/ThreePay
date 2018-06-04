package com.three.pay.paymentapi.vo.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author:luiz
 * @Date: 2018/6/4 11:15
 * @Descripton:
 * @Modify :
 **/
@Getter
@Setter
@ToString
public class PageParam {
    //当前页
    private int currPage;
    //每页显示数目
    private int pageSize;
    //倒叙或者正序显示 ASC/DESC
    private String sort;
    //排序字段
    private String orderByField;
}
