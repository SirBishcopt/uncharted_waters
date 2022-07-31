package com.sirbishcopt.unchartedwaters.service;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;
import com.sirbishcopt.unchartedwaters.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class CitiesNeedingUpdateService {

    private CityRepository cityRepository;

    public CitiesNeedingUpdateService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Map<CityName, Integer> listNamesOfCitiesAndAmountOfCommoditiesThatRequireUpdate() {
        Map<CityName, Integer> cities = new TreeMap<>();
        for (CityName cityName : CityName.values()) {
            City city = cityRepository.getReferenceById(cityName);
            int commoditiesNeedingUpdate = getAmountOfCommoditiesWithoutPrice(city);
            if (commoditiesNeedingUpdate != 0) {
                cities.put(cityName, commoditiesNeedingUpdate);
            }
        }
        return cities;
    }

    private int getAmountOfCommoditiesWithoutPrice(City city) {
        int commoditiesNeedingUpdate = 0;
        for (CommodityName commodityName : CommodityName.values()) {
            if (city.getCommodity(commodityName).getPrice() == 0) {
                commoditiesNeedingUpdate++;
            }
        }
        return commoditiesNeedingUpdate;
    }

}