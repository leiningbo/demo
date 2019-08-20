package com.example.demo.service.impl;

import com.example.demo.service.IEnum;

public enum PaySourceTypeEnum implements IEnum {
    ALIPAY("ALIPAY","支付宝"),
    WECHAT("WECHAT","微信支付"),
    UNIONPAY("UNIONPAY","银联支付宝"),
    ;
    //枚举标识码
    private String code;
    //枚举描述
    private String desc;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    PaySourceTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code 获取 value 值
     * @param code
     * @return
     */
    public static PaySourceTypeEnum getByCode(String code) {
        for (PaySourceTypeEnum value : PaySourceTypeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

}
