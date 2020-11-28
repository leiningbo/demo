package com.example.demo.service;

import com.example.demo.entity.UserLikesCount;

public interface IUserLikeCountService {

    /**
     *
     * @param userId 用户Id
     * @return 用户总获赞数
     */
    UserLikesCount selectByUserId(Long userId);

}
