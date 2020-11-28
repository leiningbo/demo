package com.example.demo.service;

import com.example.demo.entity.UserLikesList;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-11-28 16:39
 **/
public interface IUserLikesListService {

    /**
     * 检查用户是否已经给这个人点过赞
     * @param userId 用户id
     * @param getLikeUserId 获赞者id
     */
    int checkUserIsLike(Long userId,Long getLikeUserId);

    /**
     * 点赞
     * @param userLikesList xx
     */
    void userlike(UserLikesList userLikesList);

}
