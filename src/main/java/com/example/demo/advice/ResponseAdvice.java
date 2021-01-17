package com.example.demo.advice;

import com.example.demo.response.ErrorResult;
import com.example.demo.response.Result;
import com.example.demo.utils.JsonUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-10-08 17:37
 **/
@ControllerAdvice(basePackages = "com.example.demo")
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    /**
     * 是否支持advice功能
     * treu=支持，false=不支持
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     *
     * 处理response的具体业务方法
     */
    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (obj instanceof ErrorResult) {
            ErrorResult errorResult = (ErrorResult) obj;
            return Result.fail(errorResult.getStatus(),errorResult.getMessage());
        } else if (obj instanceof String) {
            return JsonUtil.object2Json(Result.suc(obj));
        }
        return Result.suc(obj);
    }
}
