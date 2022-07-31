package com.sirbishcopt.unchartedwaters.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class City {

    @Id
    @Enumerated(EnumType.STRING)
    private CityName name;
    @ElementCollection
    private List<Commodity> commodities;
    private boolean isEmpty;

    public City() {
    }

    public City(CityName name, List<Commodity> commodities, boolean isEmpty) {
        this.name = name;
        this.commodities = commodities;
        this.isEmpty = isEmpty;
    }

    public City(CityName name) {
        this.name = name;
        commodities = new ArrayList<>();
        for (CommodityName commodityName : CommodityName.values()) {
            commodities.add(new Commodity(commodityName));
        }
        this.isEmpty = Boolean.FALSE;
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

    @Override
    public String toString() {
        return "City{" +
                "name=" + name +
                ", commodities=" + commodities +
                ", isEmpty=" + isEmpty +
                '}';
    }
    
}