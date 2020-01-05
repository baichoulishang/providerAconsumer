package com.controller;

import com.alibaba.fastjson.JSON;
import com.pojo.Role;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @author 陈宜康
 * @date 2019/3/9 16:38
 * @forWhat
 */
@Controller("/myController")
@RequestMapping("/my")
public class MyController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsp/index.jsp");
        return modelAndView;
    }

    /**
     * 跳转到layui测试页面
     *
     * @return
     */
    @RequestMapping("/layuiTest")
    public ModelAndView layuiTest() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("layuiExperiment/layui.jsp");
        return modelAndView;
    }


    @RequestMapping("/goToXadmin")
    public ModelAndView goToXadmin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Xadmin/index.jsp");
        return modelAndView;
    }

    @RequestMapping("/goToVue")
    public ModelAndView goToVue() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsp/vue.jsp");
        return modelAndView;
    }


    @RequestMapping("/testConverter")
    @ResponseBody
    public String testConverter(Role role) {
        return JSON.toJSONString(role);
    }

    /**
     * 测试返回
     *
     * @return
     */
    @RequestMapping(value = "/findUser")
    public String getUser() {
        System.out.println("进来了");
        return "findUser";
    }

    /**
     * 测试接收参数
     *
     * @param: 角色对象
     * @return:
     * @auther: 不用密码的账号
     * @date: 2019/3/12 20:45
     */
    @RequestMapping("/commonParam")
    public ModelAndView commonParam(Role role) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(new MappingJackson2JsonView());
        modelAndView.addObject("role", role);
        return modelAndView;
    }


    @RequestMapping("/ModelMap")
    public String ModelMap(ModelMap modelMap) {
        System.out.println("哈哈哈");
        return "jsp/index.jsp";
    }


}
