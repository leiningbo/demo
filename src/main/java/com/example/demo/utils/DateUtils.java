package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lnb
 * @date 2021/3/15 15:16
 * @description
 */
public class DateUtils {

    /**
     * 根据日期判断本月有多少天
     * @author 半知半行
     */
    public static int dayByMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;

        switch (month) {
            case 1: case 3: case 5:case 7:  case 8:  case 10:  case 12:
                return 31;
            case 4:  case 6: case 9:  case 11:
                return 30;
            //对于2月份需要判断是否为闰年
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }

            default:
                return 0;
        }
    }

    /**
     * 输入天数 例如：1，得出：当前年月 1日 时间
     * 2021/3/1 00:00:00
     * @param day
     * @return
     * @throws ParseException
     */
    public static Date dayCalculationStartTime(int day) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        StringBuilder date = new StringBuilder();
        String year = calendar.get(Calendar.YEAR) + "-";
        date.append(year);
        String month = (calendar.get(Calendar.MONTH) + 1) + "-";
        date.append(month);
        String _day = day + "";
        date.append(_day);
        String start = " 00:00:00";
        date.append(start);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date.toString());
    }

    /**
     * 输入天数 例如：1，得出：当前年月 1日 时间
     * 2021/3/31 23:59:59
     * @param day
     * @return
     * @throws ParseException
     */
    public static Date dayCalculationEndTime(int day) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        StringBuilder date = new StringBuilder();
        String year = calendar.get(Calendar.YEAR) + "-";
        date.append(year);
        String month = (calendar.get(Calendar.MONTH) + 1) + "-";
        date.append(month);
        String _day = day + "";
        date.append(_day);
        String end = " 23:59:59";
        date.append(end);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date.toString());
    }

}
