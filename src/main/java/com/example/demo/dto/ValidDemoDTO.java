package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @program: demo
 * @description: 注解校验
 * @author: leiningbo
 * @create: 2021-01-16 21:24
 **/
@Data
public class ValidDemoDTO {

    @NotNull
    private Integer id;

    @NotBlank(message = "名称不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    private Integer age;


    private String mobile;

    @Email(message = "邮箱格式有误")
    private String email;

    @NotNull
    private BigDecimal balanceAmount;

}
