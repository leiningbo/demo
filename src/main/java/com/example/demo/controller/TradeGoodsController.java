package com.example.demo.controller;

import com.example.demo.Retention.PassToken;
import com.example.demo.entity.TradeGoods;
import com.example.demo.service.ITradeGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: leiningbo
 * @create: 2020-09-26 19:25
 **/
@RestController
@RequestMapping(value = "/tradeGoods")
public class TradeGoodsController {

    @Autowired
    private ITradeGoodsService tradeGoodsService;

    @PassToken
    @RequestMapping(value = "/queryAll")
    public List<TradeGoods> queryAll() {
        return tradeGoodsService.queryAll();
    }

}
