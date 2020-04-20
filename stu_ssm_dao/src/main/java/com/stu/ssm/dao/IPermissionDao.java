package com.stu.ssm.dao;

import com.stu.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    /**
     * 根据roleId查询中间表，得到permissionId,再根据permissionId查询Permission表，得到相应的路径对象
     * @param roleId
     * @return
     */
    /*该方法采用子查询方法
    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{roleId}")*/
    /*该方法采用内连接查询*/
    @Select("select * from system.permission a inner join system.role_permission b on a.id = b.permissionId and b.roleId = #{roleId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "permissionName",column = "permissionName"),
            @Result(property = "url",column = "url")
    })
    public List<Permission> findPermissionByRoleId(String roleId);

    /**
     * 查询所有权限信息
     * @return
     */
    @Select("select * from system.permission ")
    public List<Permission> findAll();

    /**
     * 添加权限信息
     * @param permission
     */
    @Insert("insert into system.permission (permissionName,url) values (#{permissionName},#{url})")
    void save(Permission permission);

    /**
     * 查询未有的资源路径
     * @param roleId
     * @return
     */
    @Select("select * from system.permission where id not in (select permissionId from system.role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermission(String roleId);
}
