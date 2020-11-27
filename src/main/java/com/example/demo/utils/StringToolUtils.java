package com.example.demo.utils;

import java.io.Serializable;
import java.util.Random;

/**
 * String 相关公共方法
 *
 * 2020/5/18 11点23分
 * @author  leiningbo
 * @version 6.1
 */
public class StringToolUtils implements Serializable {

    /**
     * 自定义几位数的随机数字验证码
     * @param count  几位数
     * @return  结果
     */
    public static String smsCodeByCount(int count){
        StringBuilder smsCode = new StringBuilder();
        Random random = new Random();
        if (count < 10) {
            for (int i = 0; i < count; i++) {
                int r = random.nextInt(10);
                smsCode.append(r);
            }
        }
        return smsCode.toString();
    }

    public static void main(String[] args) {
        System.out.println(StringToolUtils.smsCodeByCount(6));

    }



}
