package com.example.demo.constants;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-10-08 17:32
 **/
public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(0, "成功"),

    /* 系统500错误*/
    SYSTEM_ERROR(10000, "系统异常，请稍后重试"),
    UNAUTHORIZED(10401, "签名验证失败"),
    NO_TOKEN(10402, "无token,请重新登录"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    SEND_FAIL(10002, "发送失败"),
    USER_HAS_LIKED(10003, "用户已点过赞"),
    ILLEGAL_PARAMETER(10004, "非法参数"),


    /* 用户错误：20001-29999*/
    USER_HAS_EXISTED(20001, "用户已存在"),
    USER_NOT_FIND(20002, "用户不存在");
    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
