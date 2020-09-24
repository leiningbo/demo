package com.example.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisDemo {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public String redisTest(){
        //设置过期时间
        stringRedisTemplate.expire("name", 100, TimeUnit.SECONDS);
        //删
        stringRedisTemplate.delete("name");

        //string
        stringRedisTemplate.opsForValue().set("name","leiningbo");
        stringRedisTemplate.opsForValue().set("age","1");
        stringRedisTemplate.opsForValue().get("name");
        //原子增
        stringRedisTemplate.opsForValue().increment("stock");
        //点赞 +1
        stringRedisTemplate.opsForValue().increment("like"+"uid");
        //原子减
        stringRedisTemplate.opsForValue().decrement("stock");
        //取消点赞 点赞 -1
        stringRedisTemplate.opsForValue().decrement("like"+"uid");

        //hash
        stringRedisTemplate.opsForHash().put("key","fild","value");

        //list
        stringRedisTemplate.opsForList().leftPush("msg:990119", "10001");

        //set
        stringRedisTemplate.opsForSet().add("act:001","990119");
        stringRedisTemplate.opsForSet().add("act:001","202004","200417","001998");
        stringRedisTemplate.opsForSet().remove("act:001","001998");
        //随机取一个不重复的
        stringRedisTemplate.opsForSet().distinctRandomMembers("act:001", 1);

        //zset
        stringRedisTemplate.opsForZSet().add("zset01","",1);


        return "";
    }
}
