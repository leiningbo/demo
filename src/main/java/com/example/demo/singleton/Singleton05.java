package com.example.demo.singleton;

/**
 * 懒汉式 双检锁
 */
public class Singleton05 {
    private static volatile Singleton05 single = null;

    private Singleton05(){}

    public static Singleton05 getInstance() {
        if (single == null) {
            synchronized (Singleton05.class) {
                if (single == null) {
                    single = new Singleton05();
                }
            }
        }
        return single;
    }

}
