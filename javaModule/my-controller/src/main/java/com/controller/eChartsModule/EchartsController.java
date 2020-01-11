package com.controller.eChartsModule;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 陈宜康
 * @date 2019/3/9 16:38
 * @forWhat
 */
@Controller("/echartsController")
@RequestMapping("/echarts")
public class EchartsController {


    @RequestMapping("/main")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ECharts/echartsIndex.jsp");
        return modelAndView;
    }

}
