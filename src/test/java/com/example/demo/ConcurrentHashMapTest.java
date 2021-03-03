package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lnb
 * @date 2021/3/3 11:36
 * @description
 */
@SpringBootTest
@Slf4j
public class ConcurrentHashMapTest {

    static Map<String, Long> map=new ConcurrentHashMap<String, Long>();


    @Test
    public void test() {
        for(int i=0;i<1000;i++)
        {
            Thread t1=new Thread(new ThreadTest());
            t1.start();
        }
    }


    class ThreadTest implements Runnable
    {  //两个put中间的get得到的值有可能是1 有可能是2
        @Override
        public void run() {
            // TODO Auto-generated method stub
            ConcurrentHashMapTest body1=new ConcurrentHashMapTest();
            body1.map.put("1", 1L);
            try {
                Thread.sleep((int) (1 + Math.random() * (3 - 1)));//模拟不同的线程执行时间
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Long l1=body1.map.get("1");
            body1.map.put("1", 2L);
            Long l2=body1.map.get("1");
            System.out.println("l1:"+l1+"    l2:"+l2);
        }
    }



}
