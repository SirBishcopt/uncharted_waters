package com.sirbishcopt.unchartedwaters.domain;

public class Commodity {

    // TODO consider doing separate classes for commodities in cities and commodities in inventory
    private CommodityName name;
    private int price;
    private int amount;

    public Commodity(CommodityName name) {
        this.name = name;
        this.price = 0;
        this.amount = 0;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}