package com.example.demo.controller;

import com.example.demo.Retention.PassToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2021-01-17 23:14
 **/
@RestController
@RequestMapping("/testPort")
public class TestPortController {

    @Value("${server.port}")
    private String port;

    @PassToken
    @RequestMapping(value = "/getPort",method = RequestMethod.GET)
    public String port() {
        return "port:"+port;
    }

}
