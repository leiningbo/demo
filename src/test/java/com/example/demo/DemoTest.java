package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.Person;
import com.example.demo.entity.TradeGoods;
import com.example.demo.service.ITradeGoodsService;
import com.example.demo.utils.StringToolUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-09-26 11:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoTest {

    @Autowired
    private ITradeGoodsService iTradeGoodsService;

    @Test
    public void stringTest() {

        String s5 = "zszs";
        final String s6 = "zs";
        final String s7 = "zs";
        String s8 = s6 + s7;
        System.out.println(s5 == s8);

        String s1 = "zs";
        String s2 = "zs";
        final String s3 = s1 + s2;
        System.out.println(s5 == s3);

    }

    @Test
    public void integerTest() {

        Integer a = 128;
        Integer b = 128;
        Integer.valueOf(a);

        Integer c = new Integer(128);


        System.out.println(a == b);

    }

    @Test
    public void tradeGoodsTest() throws Exception {
        List<TradeGoods> tradeGoods = iTradeGoodsService.queryAll();
        for (TradeGoods tradeGood : tradeGoods) {
            // 反射拿对象的属性字段名、值、数据类型
            Field[] declaredFields = tradeGood.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                System.out.println(declaredField.getName());
                System.out.println(declaredField.get(tradeGood));
                System.out.println(declaredField.getType().toString());
            }

            System.out.println("tradeGoods:"+ JSON.toJSONString(tradeGood));
        }
    }

    @Test
    public void test10() throws Exception {
        Person person = new Person();
        person.setId(1L);
        person.setName("AAA");
        Person person2 = new Person();
        fieldCopy(person, person2);
//        getFields(person2);
    }

    @Test
    public void test11() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        for (Map.Entry<String, Object> map1 : map.entrySet()) {
            System.out.println(map1);
        }
        List<String> list = new ArrayList<>();
        list.add("abc");
        // 只读集合
        List<String> strings = Collections.unmodifiableList(list);
        strings.add("cba");
        System.out.println(strings.size());
        // 同步写list？
        List<String> copyList = new CopyOnWriteArrayList<>();
    }


    /** 方法--属性复制 */
    public void fieldCopy(Object source, Object target) throws Exception {
        Method[] methods = source.getClass().getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            System.out.println(methodName);
            if (methodName.startsWith("get")) {
                Object value = method.invoke(source, new Object[0]);
                System.out.println(value);
                String setMethodName = methodName.replaceFirst("(get)", "set");
                Method setMethod = Person.class.getMethod(setMethodName,
                        method.getReturnType());
                setMethod.invoke(target, value);
            }
        }
    }

    @Test
    public void test12()   {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        //保留两位有效数字
        numberFormat.setMaximumFractionDigits(2);

        List<TradeGoods> tradeGoods = iTradeGoodsService.queryAll();
        for (TradeGoods tradeGood : tradeGoods) {
            numberFormat.format(tradeGood.getGoodsPrice());
        }

        tradeGoods.forEach(item -> System.out.println(item.getGoodsPrice()));
        tradeGoods.forEach(item -> System.out.println(numberFormat.format(item.getGoodsPrice())));
        tradeGoods.forEach(item -> System.out.println(item.getForGoodPrice()));

        // 前端两种
        // parseFloat(row.weight).toFixed(4).replace(/(\d)(?=(\d{3})+\.)/g, '$1,')
        // Number(row.unitPrice).toLocaleString('en-US')

    }

    @Test
    public void test13() {
        // 近7天每天的回访数据，没有为0
//        SELECT
//        a.days,
//                ifnull( b.visitCount, 0 ) AS visitCount
//        FROM
//                (
//                        SELECT
//                        curdate( ) AS days UNION
//                        SELECT
//                        date_sub( curdate( ), INTERVAL 1 DAY ) AS days UNION
//                        SELECT
//                        date_sub( curdate( ), INTERVAL 2 DAY ) AS days UNION
//                        SELECT
//                        date_sub( curdate( ), INTERVAL 3 DAY ) AS days UNION
//                        SELECT
//                        date_sub( curdate( ), INTERVAL 4 DAY ) AS days UNION
//                        SELECT
//                        date_sub( curdate( ), INTERVAL 5 DAY ) AS days UNION
//                        SELECT
//                        date_sub( curdate( ), INTERVAL 6 DAY ) AS days
//                ) a
//        LEFT JOIN ( SELECT date( create_time ) AS datetime, count( * ) AS visitCount FROM share_visitor where sys_user_id = #{userId} GROUP BY date( create_time ) ) b ON a.days = b.datetime

        // 最近7天的 没数据查不出的
//        SELECT
//        DATE_FORMAT( create_time, '%Y-%m-%d' ) days,
//                count( * ) shareCount
//                FROM
//        ( SELECT * FROM share_path_track WHERE DATE_SUB( CURDATE( ), INTERVAL 100 DAY ) <= date( create_time ) and sys_user_id =135 ) AS dayTable
//        group by days
    }

    @Test
    public void test14() {
        boolean result = StringToolUtils.matcherNumberOrStringType("123-ewq");
        System.out.println(result);

        List<String> strings = new ArrayList<>();
        strings.add("abc");
        strings.add("def");
        strings.add("ghi");
        strings.add("jkl");
        strings.add("mno");
        System.out.println(strings);
        String s = StringToolUtils.stringArrayConcatenationComma(strings);
        System.out.println(s);
        List<String> payOrderImgUrls = new ArrayList<>(Arrays.asList(s.split(",")));
        System.out.println(payOrderImgUrls);
    }



}
