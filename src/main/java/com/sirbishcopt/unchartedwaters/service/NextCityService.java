package com.sirbishcopt.unchartedwaters.service;

import com.sirbishcopt.unchartedwaters.domain.*;
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

    // TODO Optional na końcu z errorem żeby nie null, gdy cała baza pusta
    public CityName getNextCity(Inventory inventory, boolean isLastTransaction) {

        CityName bestCityName = null;
        int profitFromBestCity = 0;

        List<City> cities;

        if (!isLastTransaction) {
            cities = leaderRepository.getCitiesThatAreNotEmpty();
        } else {
            cities = leaderRepository.getAllCities();
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