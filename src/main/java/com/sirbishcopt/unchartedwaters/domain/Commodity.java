package com.sirbishcopt.unchartedwaters.domain;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commodity commodity = (Commodity) o;
        return id == commodity.id && price == commodity.price && commodityName == commodity.commodityName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commodityName, price);
    }

}