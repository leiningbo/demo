package com.example.demo.controller;

import com.example.demo.Retention.PassToken;
import com.example.demo.Retention.UserLoginToken;
import com.example.demo.constants.ResultCode;
import com.example.demo.entity.TradeUser;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.service.IUserService;
import com.example.demo.utils.JwtUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-11-29 23:45
 **/
@RestController
@RequestMapping("/index")
public class LoginController {

    @Autowired
    private IUserService userService;

    /**
     * 登录
     */
    @PassToken
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody TradeUser user) {
        HashMap<String, Object> resultMap = new HashMap<>();
        TradeUser login = userService.login(user);
        if (login == null) {
            throw new BusinessException(ResultCode.USER_NOT_FIND);
        }
        HashMap<String, String> payload = new HashMap<>(2);
        payload.put("loginName", user.getUserName());
        payload.put("password", user.getUserPassword());
        String token = JwtUtils.getTokenSecret(payload);
        resultMap.put("token", token);
        return resultMap;
    }

    @UserLoginToken
    @ApiImplicitParams({@ApiImplicitParam(paramType="header",name="token",dataType="String",value="token",required = true,defaultValue="")})
    @RequestMapping(value = "/getUserTest", method = RequestMethod.GET)
    public HashMap<String, Object> userInfo() {
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", "success ，通过验证");
        return resultMap;
    }

}
