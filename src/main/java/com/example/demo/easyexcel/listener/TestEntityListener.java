package com.example.demo.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.fastjson.JSON;
import com.example.demo.easyexcel.domain.TestEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 * 可参考： https://blog.csdn.net/sinat_32366329/article/details/103109058
 * @program: demo
 * @description:  读取文档的监听器
 * @author: leiningbo
 * @create: 2020-09-11 21:18
 **/
@Component
@Scope(value = "prototype")
@Slf4j
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

    /**
     * 这里会一行行的返回头
     * 监听器只需要重写这个方法就可以读取到头信息
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("解析到一条头数据：{}",JSON.toJSONString(headMap));
    }

    /**
     * 监听器实现这个方法就可以在读取数据的时候获取到异常信息
     * @param exception
     * @param context
     * @throws Exception
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
            log.error("第{}行，第{}列解析异常", excelDataConvertException.getRowIndex(), excelDataConvertException.getColumnIndex());
        }
    }
}
