package com.sirbishcopt.unchartedwaters.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class City {

    private CityName name;
    private List<Commodity> commodities;
    private boolean isEmpty = Boolean.FALSE;

    public City(CityName name) {
        this.name = name;
        commodities = new ArrayList<>();
        for (CommodityName commodityName : CommodityName.values()) {
            commodities.add(new Commodity(commodityName));
        }
    }

    public Commodity getCommodity(CommodityName commodityName) {
        for (Commodity commodity : commodities) {
            if (commodity.getName() == commodityName) {
                return commodity;
            }
        }
        // TODO throw exception
        return null;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    private Commodity drawRandomCommodity(Commodity commodity1, Commodity commodity2) {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        if (randomDouble < 0.5) {
            return commodity1;
        } else {
            return commodity2;
        }
    }

}