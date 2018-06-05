package com.three.pay.paymentapi.vo.admin;

/**
 * @Author:luiz
 * @Date: 2018/6/4 11:15
 * @Descripton:
 * @Modify :
 **/
public class PageParam {
    //当前页
    private int currPage;
    //每页显示数目
    private int pageSize;
    //倒叙或者正序显示 ASC/DESC
    private String sort;
    //排序字段
    private String orderByField;

    public int getCurrPage() {
        currPage=currPage==0?currPage:currPage-1;
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrderByField() {
        return orderByField;
    }

    public void setOrderByField(String orderByField) {
        this.orderByField = orderByField;
    }
}
