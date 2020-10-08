package com.example.demo.exceptions;

import com.example.demo.constants.ResultCode;
import com.example.demo.response.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @program: demo
 * @description: 全局异常处理
 * @author: leiningbo
 * @create: 2020-10-08 22:01
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String form;


    /**
     * 处理运行时异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorResult handleThrowable(Throwable e, HttpServletRequest request) {
        //TODO 运行是异常，可以在这里记录，用于发异常邮件通知
        ErrorResult error = ErrorResult.fail(ResultCode.SYSTEM_ERROR, e);
        log.error("URL:{} ,系统异常: ", request.getRequestURI(), e);
        // 建立邮箱消息
        SimpleMailMessage message = new SimpleMailMessage();
        // 发送者
        message.setFrom(form);
        // 收件人(可多）
        message.setTo("1071781349@qq.com","26281403@qq.com");
        // 主题
        message.setSubject("生产异常");
        // 内容
        message.setText("URL:"+request.getRequestURI());
        // 发送时间
        message.setSentDate(new Date());
        javaMailSender.send(message);
        return error;
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public ErrorResult handleBusinessException(BusinessException e, HttpServletRequest request) {
        ErrorResult error = ErrorResult.builder().status(e.code)
                .message(e.message)
                .exception(e.getClass().getName())
                .build();
        log.warn("URL:{} ,业务异常:{}", request.getRequestURI(), error);
        return error;
    }

    /**
     * validator 统一异常封装
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String msgs = this.handle(e.getBindingResult().getFieldErrors());
        ErrorResult error = ErrorResult.fail(ResultCode.PARAM_IS_INVALID, e, msgs);
        log.warn("URL:{} ,参数校验异常:{}", request.getRequestURI(), msgs);
        return error;
    }

    private String handle(List<FieldError> fieldErrors) {
        StringBuilder sb = new StringBuilder();
        for (FieldError obj : fieldErrors) {
            sb.append(obj.getField());
            sb.append("=[");
            sb.append(obj.getDefaultMessage());
            sb.append("]  ");
        }
        return sb.toString();
    }

    /**
     * Assert的异常统一封装
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        ErrorResult error = ErrorResult.builder().status(4000)
                .message(e.getMessage())
                .exception(e.getClass().getName())
                .build();
        log.warn("URL:{} ,业务校验异常:{}", request.getRequestURI(), e);
        return error;
    }

}
