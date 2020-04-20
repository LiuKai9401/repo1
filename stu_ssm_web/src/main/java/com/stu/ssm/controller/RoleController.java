package com.stu.ssm.controller;

import com.stu.ssm.domain.Permission;
import com.stu.ssm.domain.Role;
import com.stu.ssm.service.IPermissionService;
import com.stu.ssm.service.IRoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService RoleService;
    @Autowired
    private IPermissionService permissionService;
    /**
     * 查询所有角色信息
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Role>  roleList = RoleService.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }

    /**
     * 添加角色信息
     * @param role
     * @return
     */
    @RequestMapping("/save.do")
    public String save(Role role){
        RoleService.save(role);
        return "redirect:findAll.do";
    }

    /**
     * 查询未存在的权限路径
     * @param roleId
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam("id") String roleId){
        ModelAndView mv = new ModelAndView();
        Role role =  RoleService.findById(roleId);
        List<Permission> otherPermssionList = permissionService.findOtherPermission(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",otherPermssionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "ids",required = true) String[] permissionIds){
        RoleService.addRoleToUser(roleId,permissionIds);
        return "redirect:findAll.do";

    }
}
