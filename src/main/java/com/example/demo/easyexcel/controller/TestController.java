package com.example.demo.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.example.demo.easyexcel.domain.TestEntity;
import com.example.demo.easyexcel.listener.TestEntityListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-09-12 09:47
 **/
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/read")
    public String readExcel() {

        return "success";
    }

    @RequestMapping(value = "/excelUpload", method = RequestMethod.POST)
    public List<TestEntity> excelUpload(MultipartFile file) throws IOException {

        TestEntityListener testEntityListener = new TestEntityListener();
        // 获取excel对象
        ExcelReaderBuilder read = EasyExcel.read(file.getInputStream(), TestEntity.class, testEntityListener);
        // 读excel对象
        read.sheet().doRead();
        List<TestEntity> data = testEntityListener.getData();
        return data;

    }
}
