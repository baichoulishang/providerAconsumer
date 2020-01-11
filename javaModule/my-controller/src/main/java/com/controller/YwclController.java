package com.controller;

import api.IYwclService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.YwclCaseVO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 陈宜康
 * @date 2019/3/9 16:38
 * @forWhat
 */
@Controller("/ywclController")
@RequestMapping("/ywcl")
public class YwclController {

    @Resource
    private IYwclService ywclService;

    /**
     * 测试返回
     *
     * @return
     */
    @RequestMapping(value = "/getList")
    @ResponseBody
    public String getList(String ldzl) {
        List<YwclCaseVO> list = ywclService.getList(ldzl);
        return JSON.toJSONString(list);
    }


}
