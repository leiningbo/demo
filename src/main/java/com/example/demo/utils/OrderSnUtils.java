package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lnb
 * @date 2021/4/14 14:56
 * @description
 */
public class OrderSnUtils {
    /**
     * 初始化订单编号
     * 例如：TX202104140001
     */
    public static String initWithdrawOrderSn() {
        StringBuilder orderSn = new StringBuilder();
        orderSn.append("TX");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        orderSn.append(date);
        orderSn.append("0001");
        return orderSn.toString();
    }

    /**
     * 传入提现单最新的一条编号 同日期增加尾数字，不同则初始化
     */
    public static String nextWithdrawOrderSn(String orderSn) {
        StringBuilder result = new StringBuilder();
        String date = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now());
        String txDate = "TX" + date;
        result.append(txDate);
        Pattern pattern = Pattern.compile("(\\d{8})(\\d{4})$");
        Matcher matcher = pattern.matcher(orderSn);
        String lastFlowNum = "";
        String lastFlowDate = "";
        if (matcher.find()) {
            lastFlowDate = matcher.group(1);
            lastFlowNum = matcher.group(2);
        }
        if (lastFlowDate.equals(date)) {
            Integer curFlowNoNumDig = Integer.parseInt(lastFlowNum) + 1;
            result.append(String.format("%0" + 4 + "d", curFlowNoNumDig));
        }else {
            result.append(String.format("%0" + 4 + "d", 1));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String s = OrderSnUtils.initWithdrawOrderSn();
        System.out.println(s);
        String s1 = OrderSnUtils.nextWithdrawOrderSn(s);
        System.out.println(s1);

    }

}
