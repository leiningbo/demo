package com.example.demo.timer;

import com.example.demo.service.impl.PaySourceTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 每10秒
     */
//    @Scheduled(cron = "*/10 * * * * ?")
    public void list(){
        List<Object> list = new ArrayList<>();
        list.add("哈哈");
        list.add("呵呵");
        list.add("嘿嘿");
        list.add("霍霍");
        list.add(PaySourceTypeEnum.ALIPAY.getDesc());
        list.add(PaySourceTypeEnum.WECHAT.getCode());
        list.add(PaySourceTypeEnum.UNIONPAY.getDesc());
        System.out.println(list);
    }



}
