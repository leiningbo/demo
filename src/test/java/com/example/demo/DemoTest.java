package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.TradeGoods;
import com.example.demo.service.ITradeGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
    public void tradeGoodsTest() {
        List<TradeGoods> tradeGoods = iTradeGoodsService.queryAll();
        for (TradeGoods tradeGood : tradeGoods) {
            System.out.println("tradeGoods:"+ JSON.toJSONString(tradeGood));
        }
    }


}
