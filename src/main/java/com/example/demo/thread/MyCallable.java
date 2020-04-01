package com.example.demo.thread;

import java.util.concurrent.Callable;

/**
 * @author leiningbo
 * 2020-04-01 13点55分
 */
public class MyCallable implements Callable {
    private int num = 10;

    @Override
    public Object call() throws Exception {
        num--;
        System.out.println(Thread.currentThread().getName() + ":" + num);
        Thread.sleep(1000);
        return num;
    }
}
