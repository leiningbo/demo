package com.example.demo.thread;

import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 继承Thread类实现线程
 *
 * @uthor leiningbo
 * 2020-04-01 13点35分
 */
public class MyThreads extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            String bundleNo = generateBundleNo("")+i;
            System.out.println(super.getName() + "Start:"+bundleNo);
        }
    }

    /**
     * 自定义捆包号
     * @return KB
     */
    public static String generateBundleNo(String lastBundle) {
        StringBuilder result = new StringBuilder();
        result.append("KB");
        String date = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
        result.append(date);
        String time = System.currentTimeMillis()+"";
        result.append(time.substring(time.length() - 4));
        if (!StringUtils.isEmpty(lastBundle)) {
            Pattern pattern = Pattern.compile("" + "(\\d{12})(\\d{4})$");
            Matcher matcher = pattern.matcher(lastBundle);
            String lastFlowNoNum = "";
            String lastFlowNoDate = "";
            if (matcher.find()) {
                lastFlowNoDate = matcher.group(1);
                lastFlowNoNum = matcher.group(2);
            }
            if (lastFlowNoDate.equals(date)) {
                Integer curFlowNoNumDig = Integer.parseInt(lastFlowNoNum) + 1;
                result.append(String.format("%0" + 4 + "d", curFlowNoNumDig));
            } else {
                result.append(String.format("%0" + 4 + "d", 1));
            }
        } else {
            result.toString();
        }
        return result.toString();
    }
}
