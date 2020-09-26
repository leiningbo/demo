package com.example.demo.service.impl;

import com.example.demo.entity.TradeGoods;
import com.example.demo.mapper.TradeGoodsMapper;
import com.example.demo.service.ITradeGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-09-26 19:21
 **/
@Service
public class ITradeGoodsServiceImpl implements ITradeGoodsService {

    @Autowired
    private TradeGoodsMapper tradeGoodsMapper;

    @Override
    public List<TradeGoods> queryAll() {
        return tradeGoodsMapper.queryAll();
    }
}
