package com.stu.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.stu.ssm.dao.IOrdersDao;
import com.stu.ssm.domain.Orders;
import com.stu.ssm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrderService {

    @Autowired
    private IOrdersDao OrdersDao;

    /**
     * 查询所有订单信息
     * @return
     */
    @Override
    public List<Orders> findAll(int pageNum,int pageSize) {
        //pageNum:起始页  pageSize:每页大小
        PageHelper.startPage(pageNum,pageSize);
        return OrdersDao.findAll();
    }

    /**
     * 查询订单详细信息
     * @param ordersId
     * @return
     */
    @Override
    public Orders findById(String ordersId) {
        return OrdersDao.findById(ordersId);
    }
}
