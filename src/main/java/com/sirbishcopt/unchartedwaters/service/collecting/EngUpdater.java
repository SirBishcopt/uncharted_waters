package com.sirbishcopt.unchartedwaters.service.collecting;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.Commodity;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;
import com.sirbishcopt.unchartedwaters.repository.CityRepository;

import java.util.regex.Pattern;

public class EngUpdater implements StringToCityUpdater {


    public void updateCityBasedOnString(String ocrName, String ocrCommodities1, String ocrCommodities2) {
        // TODO initialise properly
        CityRepository cityRepository = null;
        City city = cityRepository.getCityByName(convertStringToCityName(ocrName));
        updateCommodities(city, ocrCommodities1);
        updateCommodities(city, ocrCommodities2);
    }

    private CityName convertStringToCityName(String ocrName) {

        for (CityName cityName : CityName.values()) {
            Pattern compiledPattern = Pattern.compile(cityName.toString().toLowerCase());
            if (compiledPattern.matcher(ocrName.toLowerCase()).find()) {
                return cityName;
            }
        }

        //TODO throw exception
        return null;
    }

    private void updateCommodities(City city, String ocrCommodities) {
        String[] lines = ocrCommodities.split("/n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            for (CommodityName commodityName : CommodityName.values()) {
                Pattern compiledPatternCommodity = Pattern.compile(commodityName.toString().toLowerCase());
                if (compiledPatternCommodity.matcher(line.toLowerCase()).find()) {
                    Commodity commodity = city.getCommodity(commodityName);
                    // TODO what if i+1 does not exist
                    String nextLine = lines[i + 1];
                    String[] nextLineCut = nextLine.split(" ");

                    int price = 0;
                    for (int k = 0; k < nextLineCut.length; i++) {
                        String subLine = nextLineCut[k];
                        Pattern compiledPatternPrice = Pattern.compile("Pric");
                        if (compiledPatternPrice.matcher(subLine.toLowerCase()).find()) {
                            // TODO what if k+1 does not exist
                            String priceInString = nextLineCut[k + 1];
                            priceInString.replaceAll("[kK]", "");
                            priceInString.replace(".", "");
                            // TODO exception if priceInString is not int
                            price = Integer.parseInt(priceInString);
                        }
                    }

                    commodity.setPrice(price);
                }
            }
        }
    }

}