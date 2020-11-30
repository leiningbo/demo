package com.example.demo.Retention;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 需要登录才能进行操作的注解UserLoginToken
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-11-30 23:13
 **/
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    boolean required() default true;
}
