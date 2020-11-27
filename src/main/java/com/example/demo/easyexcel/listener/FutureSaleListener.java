package com.example.demo.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.example.demo.easyexcel.domain.FutureSaleImportEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: leiningbo
 * @description:
 * @date: Create in 10:46 2020/11/19
 */
@Component
@Slf4j
@Scope(value = "prototype")
public class FutureSaleListener extends AnalysisEventListener<FutureSaleImportEntity> {

    protected ArrayList<Map<String, Object>> header;
    protected ArrayList<Map<String, Object>> resultData;
    protected Integer maxCountOfRequired;

    Map<String, String[]> headerDict;
    List<Map<String, Object>> errorDataTitle;
    List<Map<String, Object>> errorDataList;
    /**
     * 自定义用于暂时存储data
     * 可以通过实例获取该值
     */
    private List<FutureSaleImportEntity> data = new ArrayList<>();

    /**
     * 每读一行内容，都会调用一次 invoke ，在invoke可以操作读取到的数据
     * @param futureSaleImportEntity  每次读取到的数据封装的对象
     * @param analysisContext
     */
    @Override
    public void invoke(FutureSaleImportEntity futureSaleImportEntity, AnalysisContext analysisContext) {

    }

    /**
     * 读取完整个文档之后调用的方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

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
        super.onException(exception, context);
    }

    /**
     * 返回数据
     *
     * @return 返回读取的数据集合
     **/
    public List<FutureSaleImportEntity> getData() {
        return data;
    }

}
