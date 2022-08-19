package com.sirbishcopt.unchartedwaters.domain;

import javax.persistence.*;

@Entity
public class Commodity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Enumerated(EnumType.STRING)
    private CommodityName commodityName;
    private int price;

    public Commodity() {
    }

    public Commodity(CommodityName commodityName) {
        this.commodityName = commodityName;
        this.price = 0;
    }

    public CommodityName getCommodityName() {
        return commodityName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

}