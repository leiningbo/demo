package com.example.demo.exceptions;

import com.example.demo.constants.ResultCode;
import lombok.Data;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-10-08 17:52
 **/
@Data
public class BusinessException extends RuntimeException {

    protected Integer code;

    protected String message;

    public BusinessException(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

}
