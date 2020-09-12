package com.example.demo.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.example.demo.easyexcel.domain.TestEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @program: demo
 * @description:  读取文档的监听器
 * @author: leiningbo
 * @create: 2020-09-11 21:18
 **/
@Component
@Scope(value = "prototype")
public class TestEntityListener extends AnalysisEventListener<TestEntity> {

    /**
     * 每读一行内容，都会调用一次 invoke ，在invoke可以操作读取到的数据
     * @param testEntity  每次读取到的数据封装的对象
     * @param analysisContext
     */
    @Override
    public void invoke(TestEntity testEntity, AnalysisContext analysisContext) {

        System.out.println("TestEntity:"+ JSON.toJSONString(testEntity));

    }

    /**
     * 读取完整个文档之后调用的方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }



}
