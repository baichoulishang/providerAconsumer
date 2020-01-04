package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 陈宜康
 * @date 2019/12/22 14:38
 * @forWhat
 */
@Controller
@RequestMapping("/websocket/c")
public class WebSocketController {
    /**
     * 跳转到主页面
     *
     * @return
     */
    @RequestMapping("/main")
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("WebSocket/index.jsp");
        return modelAndView;
    }
}
