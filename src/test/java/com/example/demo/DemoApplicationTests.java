package com.example.demo;

import com.example.demo.constants.DemoA.DemoAConst;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        List<Object> list = new ArrayList<>();
        list.add(DemoAConst.aConst.DEMO_A1.getCode());
        list.add(DemoAConst.aConst.DEMO_A1.getValue());
        System.out.println(list);

    }

}
