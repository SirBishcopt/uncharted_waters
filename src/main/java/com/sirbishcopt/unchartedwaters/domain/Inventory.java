package com.sirbishcopt.unchartedwaters.domain;

import java.util.Map;

public class Inventory {

    private Map<CommodityName, Integer> commodities;

    public Inventory() {
    }

    public void addCommodityAndAmount(CommodityName commodityName, Integer amount) {
        commodities.put(commodityName,amount);
    }

    public Map<CommodityName, Integer> getCommodities() {
        return commodities;
    }

}