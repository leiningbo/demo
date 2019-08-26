package com.example.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroTest {

    @Test
    public void testHasPermission() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        subject.login(token);

        /*//进行授权操作前必须登录：通过认证  true:有  false：无
        System.out.println(subject.hasRole("role1"));
        //进行授权操作前必须登录：通过认证  true:全部拥有  false：不全部拥有
        System.out.println(subject.hasAllRoles(Arrays.asList("role1","role2","role3")));
        //进行授权操作前必须登录：通过认证  true:有  false：无
        System.out.println(Arrays.toString(subject.hasRoles(Arrays.asList("role1","role2","role3"))));

        //判断当前用户是否拥有角色，如果有角色，没有返回值；没有报异常    UnauthorizedException
        subject.checkRole("role1");
        subject.checkRoles("role1","role2","role3");*/

        //判断当前用户是否拥有某个权限
        System.out.println(subject.isPermitted("user:create"));
        System.out.println(subject.isPermittedAll("user:create","user:delete"));
        System.out.println(Arrays.toString(subject.isPermitted("user:create","user:update")));

        //判断当前用户是否拥有某个权限，如果有角色，没有返回值；没有报异常    UnauthorizedException
        subject.checkPermission("user:delete");
    }

    @Test
    public void testHasRoleByRealm() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission-realm.ini");
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "666");
        subject.login(token);



        //判断当前用户是否拥有某个权限
        System.out.println(subject.isPermitted("user:create"));

        System.out.println(subject.hasRole("role1"));

    }



}
