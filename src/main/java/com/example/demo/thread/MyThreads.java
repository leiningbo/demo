package com.example.demo.thread;

/**
 * 继承Thread类实现线程
 *
 * @uthor leiningbo
 * 2020-04-01 13点35分
 */
public class MyThreads extends Thread {
    @Override
    public void run() {
        System.out.println(super.getName() + "Start");
    }
}
