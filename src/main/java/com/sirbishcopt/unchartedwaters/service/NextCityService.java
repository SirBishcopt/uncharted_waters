package com.sirbishcopt.unchartedwaters.service;

import com.sirbishcopt.unchartedwaters.domain.*;
import com.sirbishcopt.unchartedwaters.exceptions.RepositoryException;
import com.sirbishcopt.unchartedwaters.repository.LeaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class NextCityService {

    private final LeaderRepository leaderRepository;

    public NextCityService(LeaderRepository leaderRepository) {
        this.leaderRepository = leaderRepository;
    }

    public CityName getNextCity(Inventory inventory, boolean isLastTransaction) throws RepositoryException {

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
                bestCityName = drawRandomCityName(city.getCityName(), bestCityName);
            }

        }

        return bestCityName;
    }

    private CityName drawRandomCityName(CityName cityName1, CityName cityName2) {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        if (randomDouble < 0.5) {
            return cityName1;
        } else {
            return cityName2;
        }
    }

}