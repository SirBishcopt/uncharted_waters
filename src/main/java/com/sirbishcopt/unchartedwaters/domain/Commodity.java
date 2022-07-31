package com.sirbishcopt.unchartedwaters.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Commodity {

    @Enumerated(EnumType.STRING)
    private CommodityName name;
    private int price;

    public Commodity() {
    }

    public Commodity(CommodityName name, int price) {
        this.name = name;
        this.price = price;
    }

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