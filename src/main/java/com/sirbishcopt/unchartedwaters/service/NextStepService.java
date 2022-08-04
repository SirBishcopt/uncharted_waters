package com.sirbishcopt.unchartedwaters.service;

import com.sirbishcopt.unchartedwaters.domain.*;
import com.sirbishcopt.unchartedwaters.repository.LeaderRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class NextStepService {

    private LeaderRepository leaderRepository;

    public NextStepService(LeaderRepository leaderRepository) {
        this.leaderRepository = leaderRepository;
    }

    public Inventory createInventoryFromMessage(String message) {
        Inventory inventory = new Inventory();
        String[] lines = message.split("\\s+");
        for (int i = 0; i < lines.length; i++) {
            for (CommodityName commodityName : CommodityName.values()) {
                Pattern compiledPatternCommodity = Pattern.compile(commodityName.getAbbrev().toLowerCase());
                if (compiledPatternCommodity.matcher(lines[i].toLowerCase()).find()) {
                    int price = Integer.parseInt(lines[i + 1]);
                    inventory.addCommodityAndAmount(commodityName, price);
                }
            }
        }
        return inventory;
    }

    public CityName getNextStep(Inventory inventory, boolean isLastTransaction) {
        CityName bestCityName = null;
        int profitFromBestCity = 0;
        for (CityName cityName : CityName.values()) {
            // TODO consider getting list of not empty cities from repository
            City city = leaderRepository.getCityByName(cityName);
            if (!isLastTransaction && !city.isEmpty()) {
                for (Map.Entry<CommodityName, Integer> commodityFromInventory : inventory.getCommodities().entrySet()) {
                    int profit = 0;
                    Commodity commodityFromCity = city.getCommodityByName(commodityFromInventory.getKey());
                    profit += (commodityFromCity.getPrice() * commodityFromInventory.getValue());
                    if (profit > profitFromBestCity) {
                        bestCityName = cityName;
                        profitFromBestCity = profit;
                    } else if (profit == profitFromBestCity) {
                        bestCityName = drawRandomCityName(cityName, bestCityName);
                    }
                }
            } else if (isLastTransaction) {
                for (Map.Entry<CommodityName, Integer> commodityFromInventory : inventory.getCommodities().entrySet()) {
                    int profit = 0;
                    Commodity commodityFromCity = city.getCommodityByName(commodityFromInventory.getKey());
                    profit += (commodityFromCity.getPrice() * commodityFromInventory.getValue());
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