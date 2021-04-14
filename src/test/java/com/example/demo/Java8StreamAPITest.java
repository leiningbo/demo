package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lnb
 * @date 2021/4/2 9:56
 * @description
 */
//@RunWith( SpringRunner.class)
@SpringBootTest
public class Java8StreamAPITest {

    @Test
    public void test() {

        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("c");
        List<String> distinctList = strings.stream().distinct().collect(Collectors.toList());
        distinctList.forEach(System.out::print);
        distinctList.forEach(System.out::println);

    }

}
