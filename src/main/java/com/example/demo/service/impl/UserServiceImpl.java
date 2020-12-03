package com.example.demo.service.impl;

import com.example.demo.entity.TradeUser;
import com.example.demo.mapper.TradeUserMapper;
import com.example.demo.service.IUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-11-30 23:37
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private TradeUserMapper tradeUserMapper;

    @Override
    public TradeUser getById(Long id) {
        return tradeUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public TradeUser login(TradeUser user) {
        String password = new Md5Hash(user.getUserPassword()).toString();
        return tradeUserMapper.login(user.getUserName(),password);
    }
}
