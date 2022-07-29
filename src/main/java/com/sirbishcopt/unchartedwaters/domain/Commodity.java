package com.sirbishcopt.unchartedwaters.domain;

public class Commodity {

    private CommodityName name;
    private int price;

    public Commodity(CommodityName name) {
        this.name = name;
        this.price = 0;
    }

    public CommodityName getName() {
        return name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

}