package com.example.demo.service;

import com.example.demo.entity.TradeUser;

public interface IUserService {

    TradeUser getById(Long id);

    TradeUser login(TradeUser user);
}
