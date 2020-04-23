package com.example.demo.singleton;

/**
 * 登记式
 * 线程安全、防止反射功击、反序列化不安全
 */
public class Singleton03 {

    private static class SingletonHolder{
        private static Singleton03 singe = new Singleton03();
    }

    private Singleton03(){}

    public static Singleton03 getInstance() {
        return SingletonHolder.singe;
    }

}
