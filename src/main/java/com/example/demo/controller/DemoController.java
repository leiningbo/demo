package com.example.demo.controller;

import com.example.demo.Retention.PassToken;
import com.example.demo.dto.ValidDemoDTO;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author leiningbo
 * @date 2019-08-19 10点39分
 */
@RestController
@RequestMapping("/demo")
@Api(value = "DemoController", description = "你好世界controller")
public class DemoController {

    @ApiOperation(value = "测试：abo", notes = "你好世界")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "token", dataType = "String", value = "令牌", required = true, defaultValue = "")})
    @RequestMapping(value = "/helloWorld", method = RequestMethod.GET)
    public String helloWorld(@ApiParam(name = "name", value = "收货人") @RequestParam(required = false) String name,
                             @ApiParam(name = "addressId", value = "收货地址id") @RequestParam(required = false) Integer addressId) {
        return "hello world" + name + "---" + addressId;
    }

    /**
     *
     * @param validDemoDTO 数据校验
     */
    @PassToken
    @RequestMapping(value = "/validData",method = RequestMethod.POST)
    public void validData(@Valid @RequestBody ValidDemoDTO validDemoDTO) {
        System.out.println(validDemoDTO.toString());

    }


}
