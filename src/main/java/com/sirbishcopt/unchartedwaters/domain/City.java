package com.sirbishcopt.unchartedwaters.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class City {

    @Id
    @Enumerated(EnumType.STRING)
    private CityName cityName;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Commodity> commodities;
    private boolean isEmpty;

    public City() {
    }

    public City(CityName cityName, List<Commodity> commodities, boolean isEmpty) {
        this.cityName = cityName;
        this.commodities = commodities;
        this.isEmpty = isEmpty;
    }

    public City(CityName cityName) {
        this.cityName = cityName;
        commodities = new ArrayList<>();
        for (CommodityName commodityName : CommodityName.values()) {
            commodities.add(new Commodity(commodityName));
        }
        this.isEmpty = Boolean.FALSE;
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

    public boolean isEmpty() {
        return isEmpty;
    }

    public void markAsEmpty() {
        isEmpty = true;
    }

    @Override
    public String toString() {
        return "City{" +
                "name=" + cityName +
                ", commodities=" + commodities +
                ", isEmpty=" + isEmpty +
                '}';
    }

    public void addCommodity(Commodity commodity) {
        commodities.add(commodity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return isEmpty == city.isEmpty && cityName == city.cityName && Objects.equals(commodities, city.commodities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, commodities, isEmpty);
    }

}