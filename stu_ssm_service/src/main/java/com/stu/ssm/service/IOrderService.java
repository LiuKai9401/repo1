package com.stu.ssm.service;

import com.stu.ssm.domain.Orders;

import java.util.List;

public interface IOrderService {

    /**
     * 查询全部订单信息
     * @return
     */
    public List<Orders> findAll(int pageNum,int pageSize);

    /**
     * 查询订单详细信息
     * @param ordersId
     * @return
     */
    public Orders findById(String ordersId);
}
