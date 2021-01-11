package com.example.demo.utils;

import java.io.Serializable;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String 相关公共方法
 *
 * 2020/5/18 11点23分
 * @author  leiningbo
 * @version 6.1
 */
public class StringToolUtils implements Serializable {

    private static final long serialVersionUID = -8956895582762561918L;

    private static Pattern numberOfStringPattern = Pattern.compile("^[a-zA-Z0-9]+$");
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

    /**
     * 匹配只能是数字或字符串
     * @param s 传参
     * @return true or false
     */
    public static boolean matcherNumberOrStringType(String s) {
        Matcher matcher = numberOfStringPattern.matcher(s);
        return  matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(StringToolUtils.smsCodeByCount(6));

    }



}
