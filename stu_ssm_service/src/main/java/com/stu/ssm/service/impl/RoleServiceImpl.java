package com.stu.ssm.service.impl;

import com.stu.ssm.dao.IRoleDao;
import com.stu.ssm.domain.Role;
import com.stu.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<Role> findAll() {
        return  roleDao.findAll();
    }

    /**
     * 添加角色信息
     * @param role
     */
    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    /**
     * 根据userId查询未存在的角色
     * @param userId
     * @return
     */
    @Override
    public List<Role> findOtherRole(String userId) {
        return roleDao.findUserByIdAndAllRole(userId);
    }

    /**
     * 根据roleId查询角色
     * @param roleId
     * @return
     */
    @Override
    public Role findById(String roleId) {
        return roleDao.findById(roleId);
    }

    /**
     * 添加未存在的资源路径
     * @param roleId
     * @param permissionIds
     */
    @Override
    public void addRoleToUser(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addRoleToUser(roleId,permissionId);
        }

    }
}
