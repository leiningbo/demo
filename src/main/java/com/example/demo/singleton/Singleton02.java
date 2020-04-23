package com.example.demo.singleton;

/**
 * 懒汉式
 */
public class Singleton02 {

    private Singleton02() {}

    private static Singleton02 singe = null;

    public static Singleton02 getInstance() {
        if (singe == null) {
            singe = new Singleton02();
        }
        return singe;
    }

}
