package com.sirbishcopt.unchartedwaters.domain;

public class Commodity {

    private CommodityName name;
    private int price;

    public Commodity(CommodityName name, int price) {
        this.name = name;
        this.price = price;
    }

    public CommodityName getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

}