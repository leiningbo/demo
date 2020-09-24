package com.example.demo;

import com.alibaba.excel.EasyExcel;
import com.example.demo.easyexcel.domain.TestEntity;
import com.example.demo.easyexcel.listener.TestEntityListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-09-10 23:17
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EasyExcelTest {

    @Test
    public void write(){

        //简单导出 https://blog.csdn.net/weixin_37158722/article/details/102470814
        List<TestEntity> entities = new ArrayList<>();
        entities.add(new TestEntity("小C",18,"男"));
        entities.add(new TestEntity("小A",28,"女"));
        entities.add(new TestEntity("小D",21,"女"));
        entities.add(new TestEntity("小P",19,"男"));

        String fileName =  "F:\\easyExcel测试\\easyExcel_"+System.currentTimeMillis()+".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, TestEntity.class).sheet("easyExcel_01_sheet").doWrite(entities);
        log.info(fileName);

    }

    @Test
    public void read(){
        String fileName =  "F:\\easyExcel测试\\easyExcel_1599874269769.xlsx";
        // 读取一个Excel对象
        EasyExcel.read(fileName, TestEntity.class, new TestEntityListener()).sheet().doRead();

        /*ExcelReaderBuilder read = EasyExcel.read(fileName, TestEntity.class, new TestEntityListener());

        // 获取一个Excel对象
        ExcelReaderSheetBuilder sheet = read.sheet();

        // 读取Excel的内容
        sheet.doRead();*/
    }


}
