package com.sirbishcopt.unchartedwaters.service.supporting;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;
import com.sirbishcopt.unchartedwaters.repository.CityRepository;

import java.util.Map;
import java.util.TreeMap;

public class CitiesNeedingUpdateService {

    public Map<CityName, Integer> listNamesThatRequireUpdate() {
        Map<CityName, Integer> cities = new TreeMap<>();
        // TODO initialise properly
        CityRepository cityRepository = null;
        for (CityName cityName : CityName.values()) {
            City city = cityRepository.getCityByName(cityName);
            int commoditiesNeedingUpdate = 0;
            for (CommodityName commodityName : CommodityName.values()) {
                if (city.getCommodity(commodityName).getPrice() == 0) {
                    commoditiesNeedingUpdate++;
                }
            }
            if (commoditiesNeedingUpdate != 0) {
                cities.put(cityName, commoditiesNeedingUpdate);
            }
        }
        return cities;
    }

}