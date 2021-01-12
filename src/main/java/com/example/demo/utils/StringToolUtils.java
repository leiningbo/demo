package com.example.demo.utils;

import java.io.Serializable;
import java.util.List;
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

    /**
     * 字符串数组拼接逗号,去除最后一个逗号
     * @return www.baidu.com,www.google.com
     */
    public static String stringArrayConcatenationComma(List<String> strings) {
        StringBuilder stringBuilder = new StringBuilder();
        String resultString = "";
        if (strings.size() > 0) {
            for (String s : strings) {
                stringBuilder.append(s).append(",");
            }
            String result = stringBuilder.toString();
            resultString = result.substring(0, result.length() - 1);
        }
        return resultString;
    }

    public static void main(String[] args) {
        System.out.println(StringToolUtils.smsCodeByCount(6));

    }



}
