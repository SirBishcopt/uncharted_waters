package com.sirbishcopt.unchartedwaters.domain;

import java.util.List;
import java.util.Random;

public class City {

    private CityName name;
    private List<Commodity> commodities;
    private boolean isChecked = false;
    private boolean isEmpty = false;

    public City(CityName name) {
        this.name = name;
    }

    public CityName getName() {
        return name;
    }

    public List<Commodity> getCommodities() {
        return commodities;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Commodity getCheapestCommodity() {
        Commodity cheapestCommodity = commodities.get(0);
        for (Commodity commodity : commodities) {
            if (commodity.getPrice() < cheapestCommodity.getPrice()) {
                cheapestCommodity = commodity;
            } else if (commodity.getPrice() == cheapestCommodity.getPrice()) {
                cheapestCommodity = drawRandomCommodity(commodity, cheapestCommodity);
            }
        }
        return cheapestCommodity;
    }

    public Commodity getMostExpensiveCommodity() {
        Commodity mostExpensiveCommodity = commodities.get(0);
        for (Commodity commodity : commodities) {
            if (commodity.getPrice() > mostExpensiveCommodity.getPrice()) {
                mostExpensiveCommodity = commodity;
            } else if (commodity.getPrice() == mostExpensiveCommodity.getPrice()) {
                mostExpensiveCommodity = drawRandomCommodity(commodity, mostExpensiveCommodity);
            }
        }
        return mostExpensiveCommodity;
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