package com.sirbishcopt.unchartedwaters.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class City {

    @Id
    @Enumerated(EnumType.STRING)
    private CityName cityName;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Commodity> commodities;
    private boolean empty;

    public City() {
    }

    public City(CityName cityName) {
        this.cityName = cityName;
        commodities = new ArrayList<>();
        for (CommodityName commodityName : CommodityName.values()) {
            commodities.add(new Commodity(commodityName));
        }
        this.empty = Boolean.FALSE;
    }

    public Commodity getCommodityByName(CommodityName commodityName) {
        for (Commodity commodity : commodities) {
            if (commodity.getCommodityName() == commodityName) {
                return commodity;
            }
        }
        // TODO throw exception
        return null;
    }

    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public CityName getCityName() {
        return cityName;
    }

}