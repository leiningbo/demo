package com.example.demo.auth;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class PwdRealm extends AuthorizingRealm {

    private static RandomNumberGenerator generator = new SecureRandomNumberGenerator();

    @Override
    public String getName() {
        return "PwdRealm";
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
        if (!"zhangsan".equals(userName)) {
            return null;
        }
        //模拟数据库保存的加密之后的密文
        String password = "cd757bae8bd31da92c6b14c235668091";

        //info对象表示realm登录对比信息： 参数1：用户信息； 参数2：密码；参数3：盐； 参数4：当前realm对象名称
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, password, ByteSource.Util.bytes("zhangsan"),getName());
        return info;
    }
}
