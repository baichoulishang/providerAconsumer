package com.controller;

import api.IRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pojo.Role;

import javax.annotation.Resource;

/**
 * @author 陈宜康
 * @date 2020/1/11 9:26
 * @forWhat
 */
@Controller("/roleController")
@RequestMapping("/role")
public class RoleController {


    @Resource
    private IRoleService roleService;

    @RequestMapping("/add")
    public ModelAndView add(Role role) {
        ModelAndView modelAndView = new ModelAndView();
        roleService.insertRole();
        return modelAndView;
    }
}
