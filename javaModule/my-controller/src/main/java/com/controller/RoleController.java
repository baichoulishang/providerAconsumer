package com.controller;

import api.IRoleService;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.utils.JsonResult;
import com.utils.SqlMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Role;
import pojo.YwclCaseVO;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈宜康
 * @date 2019/3/9 16:38
 * @forWhat
 */
@Controller("/roleController")
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    @Resource
    private SqlMapper sqlMapper;


    /**
     * 测试返回
     *
     * @return
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public String add(Role role) {
        role.setId(IdUtil.simpleUUID());
        roleService.insertRole();
        System.out.println("插入成功");
        return "findUser";
    }

    /**
     * 得到列表
     *
     * @return
     */
    @RequestMapping("/getList")
    @ResponseBody
    public String getList() {
        List<Role> list = roleService.getList("");
        Map<String, List<Role>> listMap = new HashMap<>();
        List<YwclCaseVO> roles = sqlMapper.selectList("select*from ywcl_case", YwclCaseVO.class);
        for (YwclCaseVO y :
                roles) {
            System.out.println(y.toString());
        }
        listMap.put("list", list);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(listMap);
        jsonResult.setState(JsonResult.SUCCESS);
        return JSON.toJSONString(jsonResult);
    }

}
