package com.stu.ssm.service;

import com.stu.ssm.domain.Permission;
import com.stu.ssm.domain.Role;

import java.util.List;

public interface IPermissionService {

    /**
     * 查询所有资源权限
     * @return
     */
    public List<Permission> findAll();

    /**
     * 添加权限资源权限
     * @param permission
     */
    void save(Permission permission);

    /**
     * 查询未有的资源权限路径
     * @param roleId
     * @return
     */
    List<Permission> findOtherPermission(String roleId);
}
