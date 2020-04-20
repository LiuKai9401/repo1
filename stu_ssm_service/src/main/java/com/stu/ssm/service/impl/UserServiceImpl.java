package com.stu.ssm.service.impl;

import com.stu.ssm.dao.IUserDao;
import com.stu.ssm.domain.Role;
import com.stu.ssm.domain.UserInfo;
import com.stu.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private BCryptPasswordEncoder BCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //得到一个UserInfo对象，包含（用户的登陆信息，角色信息，路径信息）
        UserInfo userInfo = userDao.findByUsername(username);
        //把查询出的UserInfo对象，放入spring-security的User对象，该User再返回给spring-security的filter中进行验证
        /*1.User对象的第一种创建方式,3个参数：UserName(用户名）、Password（密码）、Role（角色）
        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),grantedAuthority(userInfo.getRoles()));*/
        //2.User对象的第二种方式创建，7个参数：enabled:账户是否开启,accountNonExpired:账户是否过期，credentialsNonExpired:凭证未过期，accountNonLocked:账户为锁定
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,grantedAuthority(userInfo.getRoles()));
        return user;
    }

    /**
     * 获取该用户的所有角色对象
     * @param roles
     * @return
     */
    public List<SimpleGrantedAuthority> grantedAuthority(List<Role> roles){
        //1.创建一个SimpleGrantedAuthority实体类的集合对象
        List<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();
        //2.遍历用户的角色对象
        for (Role role : roles) {
            //3.把用户的角色对象名称，放入SimpleGrantedAuthority类中
            authoritiesList.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return authoritiesList;
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    /**
     * 添加用户
     * @param userInfo
     */
    @Override
    public void save(UserInfo userInfo) {
        //进行秘密加密：使用Spring-security提供的：BCryptPasswordEncoder加密类
        userInfo.setPassword(BCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }

    /**
     * 用户添加角色
     * @param userId
     * @param roleId
     */
    @Override
    public void addRoleToUser(String userId, String [] roleId) {
        for (String role : roleId) {
            userDao.addRoleToUser(userId,role);
        }
    }
}
