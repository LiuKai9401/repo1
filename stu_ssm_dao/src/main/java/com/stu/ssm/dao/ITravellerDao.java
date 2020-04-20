package com.stu.ssm.dao;

import com.stu.ssm.domain.Traveler;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {

    /**
     * 根据订单id，查询中间表，得出旅客id,再根据旅客id查询旅客信息
     * @param ordersId
     * @return
     */
    /*采用内连接查询方式，用traveller表与中间表内连接，保持traveller.id与order_traveller.travellerId一致，再根据中间表的orderId查询旅客表信息*/
    @Select("select * from traveller a inner join order_traveller b on a.id = b.travellerId and b.orderId = #{ordersId}")
    /*采用子查询方式，先根据orderId查询出order_traveller.travellerId的值，再包裹一层查询，traveller表再进行in查询
    @Select("select * from traveller where id in (select travellerId from order_traveller id = #{ordersId})")*/
    public List<Traveler> findById(String ordersId);
}
