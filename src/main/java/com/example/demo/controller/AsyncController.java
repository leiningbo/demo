package com.example.demo.controller;

import com.example.demo.service.IAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-10-08 15:59
 **/
@RestController
@Slf4j
@RequestMapping(value = "/async")
public class AsyncController {
    @Autowired
    private IAsyncService iAsyncService;

    @RequestMapping(value = "/addScore2",method = RequestMethod.POST)
    public String createUser2() {
        log.info("--------------注册用户2--------------------");
        iAsyncService.addScore();
        return "OK";
    }

}
