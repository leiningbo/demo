package com.example.demo.easyexcel.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: demo
 *
 * @description: easy测试实体类
 *
 * @author: leiningbo
 *
 * @create: 2020-09-10 22:59
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
//@ContentRowHeight()     内容的行高
@HeadRowHeight(20)        //表头的行高
public class TestEntity {

    @ExcelProperty(value = {"Test标题","姓名"},index = 0)
//    @ColumnWidth(20)
    private String name;

    @ExcelProperty(value = {"Test标题","年龄"},index = 1)
    private Integer age;

    @ExcelProperty(value = {"Test标题","性别"}, index = 2)
    private String sex;

//    @ExcelProperty(value = "创建时间",index = 3)
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//    private Date createTime;

}
