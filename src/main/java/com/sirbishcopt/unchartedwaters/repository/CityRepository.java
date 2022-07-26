package com.sirbishcopt.unchartedwaters.repository;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.Commodity;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;

import java.util.List;

public interface CityRepository {

    void updateCity(City city);

    City getCityByName(CityName cityName);

    Commodity getCommodityByNameFromSpecificCity(CityName cityName, CommodityName commodityName);

    List<CityName> getNamesOfCitiesNeedingUpdate();

    void markCityAsEmpty(CityName cityName);

    void clearTable();

}