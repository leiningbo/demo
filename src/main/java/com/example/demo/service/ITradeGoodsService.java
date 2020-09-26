package com.example.demo.service;

import com.example.demo.entity.TradeGoods;

import java.util.List;

public interface ITradeGoodsService {
    /**
     *
     * @return 集合
     */
    List<TradeGoods> queryAll();
}
