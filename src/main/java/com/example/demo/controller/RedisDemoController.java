package com.example.demo.controller;

import com.example.demo.constants.ResultCode;
import com.example.demo.entity.UserLikesList;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.response.Result;
import com.example.demo.service.IUserLikesListService;
import com.example.demo.utils.RedisBizUtil;
import com.example.demo.utils.RedisUtils;
import com.example.demo.utils.StringToolUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-11-27 23:21
 **/
@RestController
@RequestMapping(value = "/redis")
@Api(value = "redisController")
public class RedisDemoController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private IUserLikesListService iUserLikesListService;




    /**
     * 发送注册短信验证码
     * @return
     */
    @RequestMapping(value = "/sendSmsCaptcha",method = RequestMethod.POST)
    @ResponseBody
    public Result<?> sendRegisterSms(@ApiParam(name = "mobile",value = "手机号") @RequestParam(value = "mobile") String mobile) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            //设置同手机号发送限制次数 3次之后不可再发
            Long count = redisUtils.incrBy("count:" + mobile, 1);
            assert count != null;
            int smsCount = count.intValue();
            //防止有效期覆盖
            if (smsCount == 1) {
                redisUtils.expire("count:" + mobile, 12, TimeUnit.HOURS);
            }
            if (smsCount == 4) {
                redisUtils.expire("count:" + mobile, 12, TimeUnit.HOURS);
            }
            if (smsCount > 3) {
                resultMap.put("msg", "发送失败，发送次数已用完，请在12小时后再尝试");
                return Result.suc(resultMap);
            } else {
                //随机6位数验证码
                String smsCode = StringToolUtils.smsCodeByCount(6);
                String content = "你的验证码为："+smsCode+"，请不要把验证码泄露给其他人！10分钟内有效。 【合德食品】";
//                smsService.send(mobile, content);
                //发送成功之后，将短信写入redis key：mobile  value ：smsCode  有效期十分钟
                redisUtils.setEx(mobile, smsCode, 10, TimeUnit.MINUTES);
                resultMap.put("sms",content);
                resultMap.put("msg","发送成功，请注意接收。");
                return Result.suc(resultMap);
            }
        } catch (Exception e) {
            throw new BusinessException(ResultCode.SEND_FAIL);
        }
    }

    /**
     * 点赞
     * @param userLikesList
     * @return
     */
    @RequestMapping(value = "/userLikes",method = RequestMethod.POST)
    public Result userLikes(@ApiParam(name = "userLikesList",value = "用户") @RequestBody UserLikesList userLikesList ) {
        if (userLikesList == null || userLikesList.getUserId() == null || userLikesList.getUserLikesId() == null) {
            throw new BusinessException(ResultCode.PARAM_IS_INVALID);
        }
        // 1、检查用户是否给这个用户点过赞
        int i = iUserLikesListService.checkUserIsLike(userLikesList.getUserId(), userLikesList.getUserLikesId());
        if (i < 1) {
            // 2、点赞
            String likeKey = RedisBizUtil.getLikeKey(userLikesList.getUserId());
            iUserLikesListService.userlike(userLikesList);
            return Result.suc();
        }else {
            throw new BusinessException(ResultCode.USER_HAS_LIKED);
        }
    }

}
