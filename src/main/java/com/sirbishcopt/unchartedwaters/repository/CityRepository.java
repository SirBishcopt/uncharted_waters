package com.sirbishcopt.unchartedwaters.repository;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.Commodity;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository {

    void updateCity(City city);

    City getCityByName(CityName cityName);

    // TODO is this approach better?
    // Commodity getCommodityByNameFromSpecificCity(CityName cityName, CommodityName commodityName);

    void markCityAsEmpty(CityName cityName);

    void clearTable();

}