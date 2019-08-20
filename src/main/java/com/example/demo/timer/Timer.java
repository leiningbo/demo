package com.example.demo.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务调度
 * @author leiningbo
 * @date 2019-08-20 09点20分
 */
@Component
public class Timer {

    /**
     * 每1分钟
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void list(){
        List<Object> list = new ArrayList<>();
        list.add("哈哈");
        list.add("呵呵");
        list.add("嘿嘿");
        list.add("霍霍");
        System.out.println(list);
    }


}
