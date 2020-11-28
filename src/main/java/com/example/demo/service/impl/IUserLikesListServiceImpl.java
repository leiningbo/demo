package com.example.demo.service.impl;

import com.example.demo.entity.UserLikesCount;
import com.example.demo.entity.UserLikesList;
import com.example.demo.mapper.UserLikesCountMapper;
import com.example.demo.mapper.UserLikesListMapper;
import com.example.demo.service.IUserLikesListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-11-28 16:44
 **/
@Service
@Slf4j
public class IUserLikesListServiceImpl implements IUserLikesListService {

    @Autowired
    private UserLikesListMapper userLikesListMapper;

    @Autowired
    private UserLikesCountMapper userLikesCountMapper;

    @Override
    public int checkUserIsLike(Long userId, Long getLikeUserId) {
        return userLikesListMapper.checkUserIsLike(userId, getLikeUserId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void userlike(UserLikesList userLikesList) {
        // 1、点赞
        userLikesListMapper.insert(userLikesList);
        // 2、获赞者 总赞数 +1
        UserLikesCount userLike = userLikesCountMapper.getByUserId(userLikesList.getUserLikesId());
        if (userLike == null) {
            // 总赞数插入 1
            UserLikesCount likesCount = new UserLikesCount();
            likesCount.setUserId(userLikesList.getUserLikesId());
            likesCount.setUserLikesCount(1L);
            userLikesCountMapper.insert(likesCount);
        }else {
            // 更新总赞数 +1
            userLikesCountMapper.updateUserLikeCount(userLikesList.getUserLikesId());
        }
    }
}
