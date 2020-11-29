package com.example.demo.controller;

import com.example.demo.constants.ResultCode;
import com.example.demo.entity.UserLikesCount;
import com.example.demo.entity.UserLikesList;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.service.IUserLikeCountService;
import com.example.demo.service.IUserLikesListService;
import com.example.demo.utils.RedisBizUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-11-29 10:49
 **/
@RestController
@RequestMapping(value = "/userLike")
@Api(value = "UserLikeController",description = "点赞相关")
public class UserLikeController {

    @Autowired
    private IUserLikesListService iUserLikesListService;

    @Autowired
    private IUserLikeCountService iUserLikeCountService;

    /**
     * 点赞
     * @param userLikesList 实体
     */
    @RequestMapping(value = "/userLikes",method = RequestMethod.POST)
    public void userLikes(@ApiParam(name = "userLikesList",value = "用户") @RequestBody UserLikesList userLikesList ) {
        if (userLikesList == null || userLikesList.getUserId() == null || userLikesList.getUserLikesId() == null) {
            throw new BusinessException(ResultCode.PARAM_IS_INVALID);
        }
        // 1、检查用户是否给这个用户点过赞
        int i = iUserLikesListService.checkUserIsLike(userLikesList.getUserId(), userLikesList.getUserLikesId());
        if (i < 1) {
            // 2、点赞
            String likeKey = RedisBizUtil.getLikeKey(userLikesList.getUserId());
            iUserLikesListService.userlike(userLikesList);

        }else {
            throw new BusinessException(ResultCode.USER_HAS_LIKED);
        }
    }

    /**
     *
     * @param userId 获赞者id
     * @return 获赞者info
     */
    @RequestMapping(value = "/userLikeCountSum", method = RequestMethod.GET)
    public UserLikesCount userLikeCountSum(@ApiParam(name = "userId", value = "用户id") @RequestParam(value = "userId") Long userId) {
        if (userId == null) {
            throw new BusinessException(ResultCode.PARAM_IS_INVALID);
        }
        return iUserLikeCountService.selectByUserId(userId);
    }

}
