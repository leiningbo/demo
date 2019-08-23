package com.example.demo.auth;

import org.apache.catalina.Realm;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author leiningbo
 * @date 2019-08-23 17点20分
 */
public class MyRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "MyRealm";
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //通过用户名到数据库中查用户信息  ，封装成authenticationInfo对象返回
        //获取token中的用户名
        String userName = (String) token.getPrincipal();
        if (!"admin".equals(userName)) {
            return null;
        }
        String password = "666";

        //info对象表示realm登录对比信息： 参数1：用户信息； 参数2：密码； 参数3：当前realm对象名称
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, password, getName());
        return info;
    }
}
