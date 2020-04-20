package com.stu.ssm.service.impl;

import com.stu.ssm.dao.IPermissionDao;
import com.stu.ssm.domain.Permission;
import com.stu.ssm.domain.Role;
import com.stu.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    /**
     * 查询所有资源权限
     * @return
     */
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    /**
     * 添加用户权限
     * @param permission
     */
    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public List<Permission> findOtherPermission(String roleId) {
        return permissionDao.findOtherPermission(roleId);
    }
}
