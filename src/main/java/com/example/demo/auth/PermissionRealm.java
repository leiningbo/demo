package com.example.demo.auth;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leiningbo
 * @date 2019-08-23 17点20分
 */
public class PermissionRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "PermissionRealm";
    }

    /**
     * 授权
     * @param principals  用户认证凭证信息
     *                    SimpleAuthenticationInfo：认证方法返回封装认证信息的第一个参数：用户信息(username)
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //当前登录用户信息 用户凭证
        String username = (String) principals.getPrimaryPrincipal();
        //模拟查询数据库
        //角色
        List<String> roles = new ArrayList<>();
        //权限
        List<String> permissions = new ArrayList<>();
        //假设用户在数据库中有role1角色
        roles.add("role3");
        //假设用户在数据库中有user:create权限
        permissions.add("user:create");

        //返回用户在数据库中的角色与权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);

        return info;
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
        String password = "666";

        //info对象表示realm登录对比信息： 参数1：用户信息； 参数2：密码； 参数3：当前realm对象名称
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, password, getName());
        return info;
    }
}
