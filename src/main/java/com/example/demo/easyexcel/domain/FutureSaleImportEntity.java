package com.example.demo.easyexcel.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 期货销售导入Excel 模型
 * @author: leiningbo
 * @description:
 * @date: Create in 10:01 2020/11/19
 */
@Data
public class FutureSaleImportEntity {

    @ExcelProperty(value = {"*品名"}, index = 0)
    private String productNameName;

    @ExcelProperty(value = {"品种类型"}, index = 1)
    private String categoryTypeName;

    @ExcelProperty(value = {"*规格"}, index = 2)
    private String specName;

    @ExcelProperty(value = {"*材质"}, index = 3)
    private String qualityName;

    @ExcelProperty(value = {"颜色"}, index = 4)
    private String colorName;

    @ExcelProperty(value = {"*产地"}, index = 5)
    private String originPlaceName;

    @ExcelProperty(value = {"仓库"}, index = 6)
    private String warehouseName;

    @ExcelProperty(value = {"锌层"}, index = 7)
    private String zincLayerName;

    @ExcelProperty(value = {"锌花"}, index = 8)
    private String spangleName;

    @ExcelProperty(value = {"等级"}, index = 9)
    private String gradeName;

    @ExcelProperty(value = {"材质类别"}, index = 10)
    private String qualityCategoryName;

    @ExcelProperty(value = {"质保书号"}, index = 11)
    private String qualityNumberName;

    @ExcelProperty(value = {"涂层"}, index = 12)
    private String coatingName;

    @ExcelProperty(value = {"面漆"}, index = 13)
    private String topcoatName;

    @ExcelProperty(value = {"米数"}, index = 14)
    private String meterName;

    @ExcelProperty(value = {"*预定重量(吨)"}, index = 15)
    private BigDecimal aboutWeight;

    @ExcelProperty(value = {"*预定销售单价(元)"}, index = 16)
    private BigDecimal aboutPrice;

    @ExcelProperty(value = {"备注"}, index = 17)
    private String comment;
}
