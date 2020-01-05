package com.controller;

import com.alibaba.fastjson.JSON;
import com.pojo.YwclCaseVO;
import com.ssm.service.YwclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * @author 陈宜康
 * @date 2019/3/9 16:38
 * @forWhat
 */
@Controller("/ywclController")
@RequestMapping("/ywcl")
public class YwclController {

    @Autowired
    private YwclService ywclService;

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
