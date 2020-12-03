package com.example.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MD5 {

    private static RandomNumberGenerator generator = new SecureRandomNumberGenerator();

    @Test
    public void md5Test() throws Exception{
        //盐
        String salt = generator.nextBytes().toHex();
        //明文
        String password = "a4574243";
        //md5加密
        Md5Hash md5Hash = new Md5Hash(password);
        System.out.println(md5Hash);
        //md5+盐
        Md5Hash pwd = new Md5Hash(password, salt);
        System.out.println(pwd);
        //md5+盐+散列次数
        Md5Hash finallyPwd = new Md5Hash(password, salt,3);//cd757bae8bd31da92c6b14c235668091
        System.out.println(finallyPwd);

    }

    @Test
    public void testLoginByPwdRealm() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager   模拟数据库
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-cryptography.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
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

}
