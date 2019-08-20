package com.example.demo.constants.DemoA;

/**
 * 订单类型和送货方式枚举
 */
public class DemoAConst {

    /**
     * 1、未发货  2、已发货  3、确认发货
     */
    public enum aConst{
        DEMO_A1(1, "未发货"),
        DEMO_A2(2, "已发货"),
        DEMO_A3(3, "确认收货"),
        ;
        private Integer code;
        private String value;

        public Integer getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }

        aConst(Integer code, String value) {
            this.code = code;
            this.value = value;
        }
    }

    /**
     * 1、送货上门  2、门店自提  3、物流服务
     */
    public enum bConst{
        DEMO_B1(1,"送货上门"),
        DEMO_B2(2,"门店自提"),
        DEMO_B3(3,"物流服务"),
        ;
        private Integer key;
        private String value;

        public Integer getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        bConst(Integer key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
