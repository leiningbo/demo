package com.example.demo.controller;

import com.example.demo.Retention.PassToken;
import com.example.demo.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @program: demo
 * @description: redis分布式锁
 * @author: leiningbo
 * @create: 2021-01-17 15:41
 **/
@RestController
@RequestMapping("/redisLock")
@Api(value = "Redis分布式锁")
public class DistributedRedisLockController {

    private static final String STOCK_KEY = "stock";

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private Redisson redisson;


    /**
     * 初始化库存
     * @return 库存
     */
    @PassToken
    @ApiOperation(value = "初始化库存")
    @RequestMapping(value = "/setDefaultStock",method = RequestMethod.POST)
    public String setDefaultStock() {
        String stockNumber = "200";
        redisUtils.setEx(STOCK_KEY, stockNumber, 1, TimeUnit.HOURS);
        return "初始化库存：" + redisUtils.get(STOCK_KEY);
    }

    /**
     * 减库存
     *
     * @return 标识
     */
    @PassToken
    @ApiOperation(value = "减库存")
    @RequestMapping(value = "/reduceStock", method = RequestMethod.POST)
    public String reduceStock() {
        String lockKey = "lockKey";
//        String clientId = UUID.randomUUID().toString();
        RLock redissonLock = redisson.getLock(lockKey);

        try {
//            boolean result = redisUtils.setIfAbsent(lockKey, clientId, 1, TimeUnit.MINUTES);
//            if (!result) {
//                throw new BusinessException(ResultCode.SYSTEM_ERROR);
//            }
            redissonLock.lock(); // 底层就是：redisUtils.setIfAbsent(lockKey, clientId, 1, TimeUnit.MINUTES);

            int stock = Integer.parseInt(redisUtils.get(STOCK_KEY));
            if (stock > 0) {
                int reductStock = stock - 1;
                redisUtils.setEx(STOCK_KEY, reductStock + "", 1, TimeUnit.HOURS);
                System.out.println("减库成功，剩余库存：" + stock);
            } else {
                System.out.println("减库失败，库存不足");
            }
        }finally {
            redissonLock.unlock();
//            if (clientId.equals(redisUtils.get(lockKey))) {
//                redisUtils.delete(lockKey);
//            }
        }
        return "";
    }



}
