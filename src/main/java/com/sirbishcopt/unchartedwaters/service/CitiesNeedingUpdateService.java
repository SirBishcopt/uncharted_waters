package com.sirbishcopt.unchartedwaters.service;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;
import com.sirbishcopt.unchartedwaters.repository.LeaderRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class CitiesNeedingUpdateService {

    private LeaderRepository leaderRepository;

    public CitiesNeedingUpdateService(LeaderRepository leaderRepository) {
        this.leaderRepository = leaderRepository;
    }

    public Map<CityName, Integer> listNamesOfCitiesAndAmountOfCommoditiesThatRequireUpdate() {
        Map<CityName, Integer> cities = new TreeMap<>();
        for (CityName cityName : CityName.values()) {
            City city = leaderRepository.getCityByName(cityName);
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
            if (city.getCommodityByName(commodityName).getPrice() == 0) {
                commoditiesNeedingUpdate++;
            }
        }
        return commoditiesNeedingUpdate;
    }

}