package com.example.demo.singleton;

/**
 * 枚举式
 * 线程安全、支持序列化、反序列化安全、防止反射攻击
 */
public enum Singleton04 {

    SINGLE{
        @Override
        protected void dosomething() {
            System.out.println("dosomething");
        }
    };

    protected abstract void dosomething();
}
