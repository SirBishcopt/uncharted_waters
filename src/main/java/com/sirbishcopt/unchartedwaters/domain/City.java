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
    private boolean empty;

    public City() {
    }

    public City(CityName cityName) {
        this.cityName = cityName;
        commodities = new ArrayList<>();
        CommodityName.stream()
                .map(Commodity::new)
                .forEach(commodity -> commodities.add(commodity));
    }

    public Commodity getCommodityByName(CommodityName commodityName) {
        return commodities.stream()
                .filter(commodity -> commodity.getCommodityName() == commodityName)
                .findFirst()
                .get();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return empty == city.empty && cityName == city.cityName && commodities.equals(city.commodities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, commodities, empty);
    }
}