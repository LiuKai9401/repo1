package com.stu.ssm.dao;

import com.stu.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    /**
     * 添加用户
     * @param userInfo
     */
    @Insert("insert into system.users (username,email,password,phoneNum,status) values (#{username},#{email},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    /**
     * 查询用户所拥有的：角色、路径
     * @param userName
     * @return
     */
    @Select("select * from system.users where username = #{userName}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class, many = @Many(select = "com.stu.ssm.dao.IRoleDao.findRoleByUserId")),
    })
    public UserInfo findByUsername(String userName);

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from system.users")
    public List<UserInfo> findAll();

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select * from system.users where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class, many = @Many(select = "com.stu.ssm.dao.IRoleDao.findRoleByUserId")),
    })
    public UserInfo findById(String id);

    /**
     * 用户添加角色
     * @param userId
     * @param roleId
     */
    @Insert("insert into system.users_role (userId,roleId) values (#{userId},#{roleId})")
    public void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
