package com.stu.ssm.service;

import com.stu.ssm.domain.Product;

import java.util.List;

public interface IProductService {

    /**
     * 查询所有商品
     * @return
     * @throws Exception
     */
    List<Product> findAll() throws Exception;

    /**
     * 添加商品
     * @param product
     */
    void save(Product product);
}
