package com.stu.ssm.service.impl;

import com.stu.ssm.dao.IProductDao;
import com.stu.ssm.domain.Product;
import com.stu.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao iProductDao;

    /**
     * 查询所有商品
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> findAll() throws Exception {
        return iProductDao.findAll();
    }

    /**
     * 添加商品
     * @param product
     */
    @Override
    public void save(Product product) {
        iProductDao.save(product);
    }
}
