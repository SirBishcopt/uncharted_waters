package com.sirbishcopt.unchartedwaters.domain;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private final Map<CommodityName, Integer> commodities = new HashMap<>();

    public Inventory() {
    }

    public void addCommodityAndAmount(CommodityName commodityName, Integer amount) {
        commodities.put(commodityName, amount);
    }

    public Map<CommodityName, Integer> getCommodities() {
        return commodities;
    }

}