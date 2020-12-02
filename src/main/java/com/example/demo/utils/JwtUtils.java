package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.constants.ResultCode;
import com.example.demo.exceptions.BusinessException;

import java.util.Date;
import java.util.HashMap;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-11-29 23:54
 **/
public class JwtUtils {
    /**
     * 过期时间为一天
     * TODO 正式上线更换为15分钟
     */
//    private static final long EXPIRE_TIME = 24*60*60*1000;
    private static final long EXPIRE_TIME = 60*1000;

    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "076e9ba9014e6f027269881ee06258c0";

    /**
     * 生成签名,15分钟后过期
     * @param username
     * @param userId
     * @return
     */
    public static String sign(String username,String userId){
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带username和userID生成签名
        return JWT.create().withHeader(header).withClaim("loginName",username)
                .withClaim("userId",userId).withExpiresAt(date).sign(algorithm);
    }


    public static void verity(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
        } catch (IllegalArgumentException e) {
            throw new BusinessException(ResultCode.ILLEGAL_PARAMETER);
        } catch (JWTVerificationException e) {
            throw new BusinessException(ResultCode.UNAUTHORIZED);
        }
    }

    /**
     * 获取token内的用户信息
     * @param token
     * @return
     */
    public static DecodedJWT decodedJWT(String token) {
        return JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build().verify(token);
    }

}
