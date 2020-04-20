package com.stu.ssm.dao;

import com.stu.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    @Select("select * from product where id = #{id}")
    Product findById(String id);

    /**
     * 返回所有商品
     * @return
     * @throws Exception
     */
    @Select("select * from product")
    List<Product> findAll() throws  Exception;

    /**
     * 添加商品
     * @param product
     */
    @Insert("insert into product (productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) " +
            "values" +
            "(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
