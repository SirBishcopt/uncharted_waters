package com.sirbishcopt.unchartedwaters.service;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;
import com.sirbishcopt.unchartedwaters.exceptions.RepositoryException;
import com.sirbishcopt.unchartedwaters.repository.LeaderRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

@Service
public class IncompleteCitiesService {

    private final LeaderRepository leaderRepository;

    public IncompleteCitiesService(LeaderRepository leaderRepository) {
        this.leaderRepository = leaderRepository;
    }

    public Map<CityName, Integer> listNamesOfIncompleteCitiesAndAmountOfLackingCommodities() {
        Map<CityName, Integer> incompleteCities = new TreeMap<>();
        for (CityName cityName : CityName.values()) {
            City city;
            try {
                city = leaderRepository.getCityByName(cityName);
            } catch (NoSuchElementException e) {
                throw new RepositoryException(" Cannot perform your command. Make sure you use !reset first.");
            }
            int commoditiesNeedingUpdate = getAmountOfCommoditiesWithPriceEqualsZero(city);
            if (commoditiesNeedingUpdate != 0) {
                incompleteCities.put(cityName, commoditiesNeedingUpdate);
            }
        }
        return incompleteCities;
    }

    private int getAmountOfCommoditiesWithPriceEqualsZero(City city) {
        return (int) CommodityName.stream()
                .map(city::getCommodityByName)
                .filter(commodity -> commodity.getPrice() == 0)
                .count();
    }

}