package com.sirbishcopt.unchartedwaters.service.supporting;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.Commodity;
import com.sirbishcopt.unchartedwaters.repository.CityRepository;

import java.util.List;
import java.util.Random;

public class NextStepService {

    public CityName getNextStep(List<Commodity> commodities, boolean isLastTransaction) {
        CityName bestCityName = null;
        int profitFromBestCity = 0;
        // TODO initialise properly
        CityRepository cityRepository = null;
        for (CityName cityName : CityName.values()) {
            if (!isLastTransaction && !cityRepository.getCityByName(cityName).isEmpty()) {
                City city = cityRepository.getCityByName(cityName);
                int profit = 0;
                for (Commodity commodityFromUser : commodities) {
                    Commodity commodityFromCity = city.getCommodity(commodityFromUser.getName());
                    profit = +commodityFromCity.getPrice() * commodityFromUser.getAmount();
                }
                if (profit > profitFromBestCity) {
                    bestCityName = cityName;
                    profitFromBestCity = profit;
                } else if (profit == profitFromBestCity) {
                    bestCityName = drawRandomCityName(cityName, bestCityName);
                }
            } else if (isLastTransaction) {
                if (!isLastTransaction && !cityRepository.getCityByName(cityName).isEmpty()) {
                    City city = cityRepository.getCityByName(cityName);
                    int profit = 0;
                    for (Commodity commodityFromUser : commodities) {
                        Commodity commodityFromCity = city.getCommodity(commodityFromUser.getName());
                        profit = +commodityFromCity.getPrice() * commodityFromUser.getAmount();
                    }
                    if (profit > profitFromBestCity) {
                        bestCityName = cityName;
                        profitFromBestCity = profit;
                    } else if (profit == profitFromBestCity) {
                        bestCityName = drawRandomCityName(cityName, bestCityName);
                    }
                }
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