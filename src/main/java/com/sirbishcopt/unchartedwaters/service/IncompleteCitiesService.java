package com.sirbishcopt.unchartedwaters.service;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;
import com.sirbishcopt.unchartedwaters.repository.LeaderRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class IncompleteCitiesService {

    private LeaderRepository leaderRepository;

    public IncompleteCitiesService(LeaderRepository leaderRepository) {
        this.leaderRepository = leaderRepository;
    }

    public Map<CityName, Integer> listNamesOfIncompleteCitiesAndAmountOfLackingCommodities() {
        Map<CityName, Integer> incompleteCities = new TreeMap<>();
        for (CityName cityName : CityName.values()) {
            City city = leaderRepository.getCityByName(cityName);
            int commoditiesNeedingUpdate = getAmountOfCommoditiesWithPriceEqualsZero(city);
            if (commoditiesNeedingUpdate != 0) {
                incompleteCities.put(cityName, commoditiesNeedingUpdate);
            }
        }
        return incompleteCities;
    }

    private int getAmountOfCommoditiesWithPriceEqualsZero(City city) {
        int commoditiesWithPriceEqualsZero = 0;
        for (CommodityName commodityName : CommodityName.values()) {
            if (city.getCommodityByName(commodityName).getPrice() == 0) {
                commoditiesWithPriceEqualsZero++;
            }
        }
        return commoditiesWithPriceEqualsZero;
    }

}