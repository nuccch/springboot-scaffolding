package org.chench.springboot.scaffolding.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 示例定时任务
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.task.SampleTask
 * @date 2023.07.17
 */
@Component
public class SampleTask {
    //@Scheduled(cron = "0 0/10 * * * ?") // 通过cron表达式设置定时策略
    @Scheduled(fixedRate = 10000) // 设置固定周期，单位：毫秒
    public void execute() {
        System.out.println(String.format("now: %s", new Date()));
    }
}
