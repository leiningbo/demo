package com.example.demo.singleton;

/**
 * 单例模式，饿汉式
 * 线程安全、反射不安全、反序列化不安全
 */
public class Singleton01 {

    //2、提供一个当前类的静态实例属性
    private static Singleton01 single = new Singleton01();

    //1、提供一个私有化的构造方法
    private Singleton01(){}

    //3、对外提供一个公有的获取当前对象的方法
    public static Singleton01 getInstance() {
        return single;
    }

    //反序列化的时候是不安全的，为了解决这个问题，我们可以写一个readResolve()方法
    private Object readResolve(){
        return single;
    }



}
