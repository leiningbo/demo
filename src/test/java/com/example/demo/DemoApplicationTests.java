package com.example.demo;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.example.demo.constants.DemoA.DemoAConst;
import com.example.demo.entity.Stationery;
import com.example.demo.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.Factory;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Test
    public void contextLoads() {
        List<Object> list = new ArrayList<>();
        list.add(DemoAConst.aConst.DEMO_A1.getCode());
        list.add(DemoAConst.aConst.DEMO_A1.getValue());
        System.out.println(list);

    }

    @Test
    public void getSalt() {
        String salt = randomNumberGenerator.nextBytes().toHex();
        System.out.println("salt:" + salt);
    }

    @Test
    public void testHelloWorld() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager   模拟数据库
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123");
        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
        }
        System.out.println("登陆是否成功：" + subject.isAuthenticated());
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出登录
        subject.logout();

    }

    @Test
    public void testLoginByRealm() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager   模拟数据库
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123");
        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
        }
        System.out.println("登陆是否成功：" + subject.isAuthenticated());
//        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出登录
        subject.logout();

    }

    /**
     * java8流式语法
     * <p>
     * map
     * <p>
     * filter
     * <p>
     * Optional 防空利器    说白了把if-else语法的压扁了
     * <p>
     * findFirst
     * <p>
     * ::方法引用
     * <p>
     * limit
     * <p>
     * skip
     */
    @Test
    public void java8() {
        List<Stationery> a = Lists.newArrayList(
                new Stationery("钢笔", "红本")
                , new Stationery("钢笔", "本子"));
        List<Stationery> b = Lists.newArrayList(
                new Stationery("圆珠笔", "绿本"));
        List<Stationery> c = Lists.newArrayList(
                new Stationery("钢笔", "蓝子"),
                new Stationery("铅笔", "黄本"));

        List<Student> students = new ArrayList<>();
        students.add(new Student("001", "程序汪", "男", a, 18));
        students.add(new Student("002", "小红", "女", b, 11));
        students.add(new Student("003", "小美", "女", c, 10));
        //集合防空
        Optional
                .ofNullable(students)
                .orElse(Collections.emptyList()).forEach(System.out::println);

        /**
         * Optional+findFirst
         * 集合中找一条数据
         */
        Optional<String> oFindFirst = students.stream()
                .filter(Objects::nonNull)
                .map(Student::getName)
                .filter(f -> StringUtils.equals(f, "程序汪"))
                .findFirst();

        Optional<String> abc = students.stream().filter(Objects::nonNull).map(Student::getName).filter(f -> StringUtils.equals(f, "小红")).findFirst();
        if (abc.isPresent()) {
            System.out.println("hello" + abc.get());
        }
        if (oFindFirst.isPresent()) {
            System.out.println("给" + oFindFirst.get() + "发奖金啦");
        }

        oFindFirst.ifPresent(f -> {
            System.out.println(f + "来了");
        });

        //filter过滤
        students.stream().filter(Objects::nonNull).forEach(f -> {
            System.out.println(f.getName() + "|" + f.getSex() + "|" + f.getAge());
        });

        //map 集合变形
        List<String> names = students.stream().filter(Objects::nonNull).map(Student::getName).collect(Collectors.toList());
        names.forEach(System.out::println);

        /**
         * flatMap扁平化
         * 和map的区别是，map是1对1的，flatMap是1对多
         *
         * 相等有集合list.add 和list.addAll区别
         *
         * 如下把学生集合中文具集合属性，扁平化在输出
         *
         * 对应复杂的集合嵌套类，处理起来非常爽
         */
        students.stream()
                .filter(Objects::nonNull)
                .map(Student::getStationerys)
                .flatMap(List::stream)
                .forEach(System.out::println);

        // list转Map
        Map<String, Student> map = students.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Student::getStudentId, s -> s));
        System.out.println(JSON.toJSON(map));

        // list转Map分组
        Map<String, List<Student>> map2 = students.stream().filter(Objects::nonNull).collect(Collectors.groupingBy(stu -> (stu.getSex())));
        System.out.println(JSON.toJSON(map2));

        /**
         * limit
         * 当集合空是limit返回的是一个空集合不是null
         *
         * 这样不容易NEP
         */
        List<String> names2 = students.stream().filter(Objects::nonNull).map(Student::getName).filter(f -> StringUtils.equals(f, "aaa")).limit(1).collect(Collectors.toList());
        names2.forEach(System.out::println);


        //limit+skip类似mysql的limit关键字用法
        List<String> names33 = students.stream()
                .filter(Objects::nonNull)
                .map(Student::getName)
                .skip(2)
                .limit(1).collect(Collectors.toList());
        names33.forEach(System.out::println);

        //集合排序
        Collections.sort(students, Comparator.comparing(Student::getAge));
        students.forEach(System.out::println);
    }

    @Test
    public void test() {
        List<Object> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            System.out.println("hah");
        } else {
            System.out.println("heh");
        }

        String abc = "abc";
        if (StringUtils.isEmpty(abc)) {
            System.out.println("无");
        } else {
            System.out.println("abc");
        }
    }

    @Test
    public void test1() {

        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.forEach((key,value)->{
            if (StringUtils.equals("1", key)) {
                System.out.println("key:"+key+",value:"+value);
            }
        });
        for(Map.Entry<String, String> m :map.entrySet()){
            System.out.println("key:"+m.getKey()+",value:"+m.getValue());
        }
    }

    @Test
    public void test2() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalTime now1 = LocalTime.now();
        System.out.println(now1);

        Instant now2 = Instant.now();
        System.out.println(now2);
        String time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        System.out.println(time);

    }




}
