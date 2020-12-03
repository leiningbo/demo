package com.example.demo.interceptor;

import com.example.demo.Retention.PassToken;
import com.example.demo.Retention.UserLoginToken;
import com.example.demo.constants.ResultCode;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.service.IUserService;
import com.example.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 1.从 http 请求头中取出 token，
 * 2.判断是否映射到方法
 * 3.检查是否有passtoken注释，有则跳过认证
 * 4.检查有没有需要用户登录的注解，有则需要取出并验证
 * 5.认证通过则可以访问，不通过会报相关错误信息
 *
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-11-30 22:57
 **/
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    /**
     * 预处理回调方法,实现处理器的预处理
     * @param request
     * @param response
     * @param object
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        //支持跨域请求
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,X-Nideshop-Token,X-URL-PATH");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        // 从请求头拿到token
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        // 检查方法是否被 @PassToken修饰，有则跳过验证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 检查有没有需要用户权限的注解 @UserLoginToken
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new BusinessException(ResultCode.NO_TOKEN);
                }
                // 验证token
                JwtUtils.verity(token);
                // 从token中拿userId
                /*String userId;
                try {
                    userId = JwtUtils.decodedJWT(token).getClaim("userId").asString();
                } catch (JWTDecodeException e) {
                    throw new BusinessException(ResultCode.UNAUTHORIZED);
                }
                TradeUser user = userService.getById(Long.valueOf(userId));
                if (user == null) {
                    throw new BusinessException(ResultCode.USER_NOT_FIND);
                }*/
                return true;
            }
        }
        return false;
    }
}
