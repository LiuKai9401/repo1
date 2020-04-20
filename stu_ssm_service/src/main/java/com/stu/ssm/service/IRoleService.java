package com.stu.ssm.service;

import com.stu.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    /**
     * 查询所有方法
     * @return
     */
    public List<Role> findAll();

    /**
     * 添加角色
     * @param role
     */
    void save(Role role);

    /**
     * 根据userId查询出中间表中的roleId，再排除roleId得出全部未有的角色
     * @param userId
     * @return
     */
    List<Role> findOtherRole(String userId);

    /**
     * 根据roleId查询角色
     * @param roleId
     * @return
     */
    Role findById(String roleId);

    /**
     * 添加未存在的资源路径
     * @param roleId
     * @param permissionIds
     */
    void addRoleToUser(String roleId, String[] permissionIds);
}
