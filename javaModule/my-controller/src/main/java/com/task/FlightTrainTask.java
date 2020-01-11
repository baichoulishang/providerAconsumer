package com.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 陈宜康
 * @date 2019/12/8 17:04
 * @forWhat
 */
@Component
public class FlightTrainTask {
    @Scheduled(cron = "0/5 * * * * ? ") // 间隔5秒执行
    public void taskCycle() {
        // System.out.println("使用SpringMVC框架配置定时任务");
    }
}
