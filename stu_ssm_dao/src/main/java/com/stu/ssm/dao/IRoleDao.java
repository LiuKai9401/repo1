package com.stu.ssm.dao;

import com.stu.ssm.domain.Permission;
import com.stu.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    /**
     * 根据userId查询中间表，得到roleId,再根据roleId查询Role表，得到对应的权限对象
     *
     * @param userId
     * @return
     */
    /* 该方法采用子查询方法
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId} ")*/
    /* 该方法采用内连接查询*/
    @Select("select * from system.role a inner join system.users_role b on a.id = b.roleId and b.userId = #{userId} ")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.stu.ssm.dao.IPermissionDao.findPermissionByRoleId")),
    })
    public List<Role> findRoleByUserId(String userId);

    /**
     * 查询所有方法
     * @return
     */
    @Select("select * from system.role")
    public List<Role> findAll();

    /**
     * 添加角色
     * @param role
     */
    @Insert("insert into system.role (roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * 查询出不包含的角色
     * @param userId
     * @return
     */
    @Select("select * from system.role where id not in (select roleId from system.users_role where userId = #{userId})")
    List<Role> findUserByIdAndAllRole(String userId);

    /**
     * 根据id查询角色
     * @param roleId
     * @return
     */
    @Select("select * from system.role where id = #{roleId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class ,one = @One(select = ("com.stu.ssm.dao.IPermissionDao.findPermissionByRoleId")))
    })
    public Role findById(String roleId);

    /**
     * 添加未存在的资源路径
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into system.role_permission (roleId,permissionId) values (#{roleId},#{permissionId})")
    void addRoleToUser(@Param("roleId")String roleId,@Param("permissionId") String permissionId);
}
