package com.stu.ssm.controller;

import com.stu.ssm.domain.Permission;
import com.stu.ssm.domain.Role;
import com.stu.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    /**
     * 查询所有资源权限
     * @param permission
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(PermissionController permission){
        ModelAndView mv = new ModelAndView();
        List<Permission>  permissionsList = permissionService.findAll();
        mv.addObject("permissionsList",permissionsList);
        mv.setViewName("permission-list");
        return mv;
    }

    /**
     * 添加权限
     * @param permission
     * @return
     */
    @RequestMapping("/save.do")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:findAll.do";
    }
}
