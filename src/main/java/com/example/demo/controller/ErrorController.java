package com.example.demo.controller;

import com.example.demo.constants.ResultCode;
import com.example.demo.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-10-08 21:55
 **/
@RestController
@RequestMapping(value = "/businessException")
@Slf4j
public class ErrorController {

    @RequestMapping(value = "/error1",method = RequestMethod.POST)
    public void error1() {
        throw new RuntimeException("用户已存在");
    }

    @RequestMapping(value = "/error2",method = RequestMethod.POST)
    public void error2() {
        throw new BusinessException(ResultCode.USER_NOT_FIND);
    }

}
