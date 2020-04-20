package com.stu.ssm.service;

import com.stu.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    /**
     * 获取全部用户信息
     * @return
     */
   public List<UserInfo> findAll();

    /**
     * 添加用户信息
     * @param userInfo
     */
    void save(UserInfo userInfo);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    UserInfo findById(String id);

    /**
     * 用户添加角色
     * @param userId
     * @param roleId
     */
    void addRoleToUser(String userId, String[] roleId);
}
