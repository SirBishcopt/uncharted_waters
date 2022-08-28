package com.sirbishcopt.unchartedwaters.service;

import com.sirbishcopt.unchartedwaters.domain.*;
import com.sirbishcopt.unchartedwaters.exceptions.RepositoryException;
import com.sirbishcopt.unchartedwaters.repository.LeaderRepository;
import com.sirbishcopt.unchartedwaters.utils.RandomUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NextCityService {

    private final LeaderRepository leaderRepository;

    public NextCityService(LeaderRepository leaderRepository) {
        this.leaderRepository = leaderRepository;
    }

    public CityName getNextCity(Inventory inventory, boolean isLastTransaction) {

        CityName bestCityName = null;
        int profitFromBestCity = 0;

        List<City> cities;

        if (isLastTransaction) {
            cities = leaderRepository.getAllCities();
            if (cities.isEmpty()) {
                throw new RepositoryException(" Cannot perform your command. Make sure you use !reset first.");
            }
        } else {
            cities = leaderRepository.getCitiesThatAreNotEmpty();
            if (cities.isEmpty()) {
                throw new RepositoryException(" Cannot perform your command. All cities are marked as empty. Use !last command.");
            }
        }

        for (City city : cities) {

            int profit = 0;

            for (Map.Entry<CommodityName, Integer> commodityFromInventory : inventory.getCommodities().entrySet()) {
                Commodity commodityFromCity = city.getCommodityByName(commodityFromInventory.getKey());
                profit += (commodityFromCity.getPrice() * commodityFromInventory.getValue());
            }

            if (profit > profitFromBestCity) {
                bestCityName = city.getCityName();
                profitFromBestCity = profit;
            } else if (profit == profitFromBestCity) {
                bestCityName = RandomUtil.drawRandomCityNameOfTwo(city.getCityName(), bestCityName);
            }

        }

        return bestCityName;
    }

}