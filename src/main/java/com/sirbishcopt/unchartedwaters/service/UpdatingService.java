package com.sirbishcopt.unchartedwaters.service;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.Commodity;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;
import com.sirbishcopt.unchartedwaters.repository.LeaderRepository;
import com.sirbishcopt.unchartedwaters.service.ocr.OcrService;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UpdatingService {

    LeaderRepository leaderRepository;
    OcrService ocrService;

    public UpdatingService(LeaderRepository leaderRepository, OcrService ocrService) {
        this.leaderRepository = leaderRepository;
        this.ocrService = ocrService;
    }

    public void updateCity(CityName cityName, String[] attachments) {
        City city = leaderRepository.getCityByName(cityName);
        String ocrText = ocrService.doOcr(attachments);
        city = updateCommodities(city, ocrText);
        leaderRepository.save(city);
    }

    public void markCityAsEmpty(CityName cityName) {
        leaderRepository.markCityAsEmpty(cityName);
    }

    private City updateCommodities(City city, String ocrCommodities) {

        String[] ocrCommoditiesInLines = ocrCommodities.split("\n");

        for (int i = 0; i < ocrCommoditiesInLines.length; i++) {

            for (CommodityName commodityName : CommodityName.values()) {
                Pattern compiledPatternCommodity = Pattern.compile(commodityName.getAbbrev().toLowerCase());
                if (compiledPatternCommodity.matcher(ocrCommoditiesInLines[i].toLowerCase()).find()) {
                    Commodity commodity = city.getCommodityByName(commodityName);
                    // TODO what if i+1 does not exist
                    setPriceBasedOnOcr(commodity, ocrCommoditiesInLines[i + 1]);
                }
            }

        }

        return city;
    }

    private void setPriceBasedOnOcr(Commodity commodity, String ocrLineWithPrice) {

        String[] ocrLineWithPriceInLines = ocrLineWithPrice.split("\\s+");

        for (int k = 0; k < ocrLineWithPriceInLines.length; k++) {
            Pattern compiledPatternPrice = Pattern.compile("Pric");
            if (compiledPatternPrice.matcher(ocrLineWithPriceInLines[k]).find()) {
                // TODO what if k+1 does not exist

                int price = ConvertStringToInt(ocrLineWithPriceInLines[k + 1]);

                commodity.setPrice(price);
                break;
            }
        }
    }

    private int ConvertStringToInt(String priceInString) {

        Pattern compiledPatternThousand = Pattern.compile("k");
        if (compiledPatternThousand.matcher(priceInString.toLowerCase()).find()) {
            priceInString = priceInString.replaceAll("[.]", "").replaceAll("[kK]", "");
        }

        try {
            return Integer.parseInt(priceInString);
        } catch (NumberFormatException e) {
            return 0;
        }

    }

}