package com.example.demo.controller;

import com.example.demo.Retention.PassToken;
import com.example.demo.entity.TradeUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-11-29 23:45
 **/
@Controller
@RequestMapping("/index")
public class LoginController {

    /**
     * 登录
     *
     * @param params 参数
     */
    @PassToken
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestBody Map<String, String> params) {
        String loginName = params.get("loginName");
        String loginPwd = params.get("loginPwd");


    }

    @RequestMapping(value = "/getUserByToken", method = RequestMethod.POST)
    public TradeUser userInfo(HttpServletRequest request, @RequestBody Map<String, String> params) {
        String loginName = params.get("loginName");
        String token = request.getHeader("token");


        return null;
    }

}
