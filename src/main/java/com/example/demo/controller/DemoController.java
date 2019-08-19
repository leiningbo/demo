package com.example.demo.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leiningbo
 * @date 2019-08-19 10点39分
 */
@RestController
@RequestMapping("/demo")
@Api(value = "DemoController",description = "你好世界controller")
public class DemoController {

    @ApiOperation(value = "测试：abo",notes = "你好世界")
//    @ApiImplicitParams({@ApiImplicitParam(paramType="header",name="token",dataType="String",value="令牌",required = true,defaultValue="")})
    @RequestMapping(value = "/helloWorld",method = RequestMethod.GET)
    public String helloWorld() {
        return "hello world";
    }

}
