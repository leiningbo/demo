package com.example.demo;

import com.example.demo.constants.DemoA.DemoAConst;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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
    public void getSalt(){
        String salt = randomNumberGenerator.nextBytes().toHex();
        System.out.println("salt:"+salt);
    }

    @Test
    public void testHelloWorld(){
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
            log.info("登陆成功");
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
            log.error("登陆失败");
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出登录
        subject.logout();

    }

}
