package com.sirbishcopt.unchartedwaters.domain;

import java.util.Map;
import java.util.TreeMap;

public class Inventory {

    private Map<CommodityName, Integer> commodities = new TreeMap<>();

    public Inventory() {
    }

    public void addCommodityAndAmount(CommodityName commodityName, Integer amount) {
        commodities.put(commodityName, amount);
    }

    public Map<CommodityName, Integer> getCommodities() {
        return commodities;
    }

}