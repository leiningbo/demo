package com.example.demo.service.impl;

import com.example.demo.service.IAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-10-08 15:55
 **/
@Service
@Slf4j
public class AsyncServiceImpl implements IAsyncService {

    @Async(value = "scorePoolExecutor")
    @Override
    public void addScore() {
        try {
            // 模拟睡5秒，用于赠送积分处理
            Thread.sleep(1000*5);
            log.info("-------处理积分------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
