package com.example.demo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.HashMap;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-11-29 23:30
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JsonWebTokenTest {

    @Test
    public void test1() {
        String userId = "123456789";
        String userName = "abo";
        // 头部信息  默认也是这个（可不写）
        HashMap<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        // 过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 15);
        // 私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256("076e9ba9014e6f027269881ee06258c0");
        // header 头部信息
        // claim payload 可以携带的有效数据（请不要使用敏感数据）
        // expiresAt 设置过期时间
        // sign 签名 相当于密钥的私钥，请保密
        String token = JWT.create()
                .withHeader(headerMap)
                .withClaim("userId", userId)
                .withClaim("userName", userName)
                .withExpiresAt(calendar.getTime())
                .sign(algorithm);

        System.out.println("token:"+token);
    }

    @Test
    public void test2() {
        // 创建验证对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("076e9ba9014e6f027269881ee06258c0")).build();
        DecodedJWT verify = jwtVerifier.verify("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImFibyIsImV4cCI6MTYwNjY2NzExMiwidXNlcklkIjoiMTIzNDU2Nzg5In0.694uqxSbDI5IQ4T0_sm7yniCny1RQFzutTOkzkZ6U9s");
        System.out.println(verify.getClaim("userId").asString());
        System.out.println(verify.getClaim("userName").asString());
    }

}
