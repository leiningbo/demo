package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lnb
 * @date 2021/4/16 9:36
 * @description
 */
@RunWith( SpringRunner.class)
@Slf4j
public class DateAPITest {


    @Test
    public void test() {
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        System.out.println(date);
        System.out.println(LocalDateTime.now());

    }

    @Test
    public void test1() {
        BigDecimal bigDecimal = BigDecimal.valueOf(100);
        System.out.println(bigDecimal);
        
    }

}
