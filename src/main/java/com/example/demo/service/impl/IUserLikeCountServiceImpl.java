package com.example.demo.service.impl;

import com.example.demo.entity.UserLikesCount;
import com.example.demo.mapper.UserLikesCountMapper;
import com.example.demo.service.IUserLikeCountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-11-28 21:20
 **/
@Service
@Slf4j
public class IUserLikeCountServiceImpl implements IUserLikeCountService {

    @Autowired
    private UserLikesCountMapper userLikesCountMapper;

    @Override
    public UserLikesCount selectByUserId(Long userId) {

        return userLikesCountMapper.getByUserId(userId);
    }
}
