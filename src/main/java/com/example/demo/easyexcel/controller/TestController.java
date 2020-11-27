package com.example.demo.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.example.demo.easyexcel.domain.TestEntity;
import com.example.demo.easyexcel.listener.TestEntityListener;
import com.example.demo.service.ITradeGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
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

    @Autowired
    private ITradeGoodsService tradeGoodsService;

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

    @RequestMapping(value = "/excelExport",method = RequestMethod.GET)
    public String excelExport(HttpServletResponse response) throws IOException {
        List<TestEntity> entities = new ArrayList<>();
        entities.add(new TestEntity("小C",18,"男"));
        entities.add(new TestEntity("小A",28,"女"));
        entities.add(new TestEntity("小D",21,"女"));
        entities.add(new TestEntity("小P",19,"男"));
        // 获取excel对象
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("demo", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"),"ISO8859-1") + ".xlsx");
        EasyExcel.write(response.getOutputStream(),TestEntity.class).sheet().doWrite(entities);
        // 前端可用 window.location.href = "../inventoryConfirm/futurePurchaseGoods/exportData?futureContractId="+vm.futureContractId;
        // 前端可用 window.location.href = "../test/excelExport";
        return "success";

    }


}
