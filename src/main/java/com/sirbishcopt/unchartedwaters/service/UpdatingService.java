package com.sirbishcopt.unchartedwaters.service;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.Commodity;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;
import com.sirbishcopt.unchartedwaters.exceptions.OcrServiceException;
import com.sirbishcopt.unchartedwaters.exceptions.RepositoryException;
import com.sirbishcopt.unchartedwaters.repository.LeaderRepository;
import com.sirbishcopt.unchartedwaters.service.ocr.OcrService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

@Service
public class UpdatingService {

    private final LeaderRepository leaderRepository;
    private final OcrService ocrService;

    public UpdatingService(LeaderRepository leaderRepository, OcrService ocrService) {
        this.leaderRepository = leaderRepository;
        this.ocrService = ocrService;
    }

    public void updateCity(CityName cityName, String[] attachments) {
        City city;
        try {
            city = leaderRepository.getCityByName(cityName);
        } catch (NoSuchElementException e) {
            throw new RepositoryException(" Cannot perform your command. Make sure you use !reset first.");
        }
        try {
            String ocrText = ocrService.doOcr(attachments);
            city = updateCommodities(city, ocrText);
        } catch (IOException | NoSuchElementException | TesseractException | IndexOutOfBoundsException e) {
            throw new OcrServiceException(" I've encountered problems while reading your screenshots.");
        }
        leaderRepository.save(city);
    }

    public void markCityAsEmpty(CityName cityName) {
        try {
            leaderRepository.markCityAsEmpty(cityName);
        } catch (NoSuchElementException e) {
            throw new RepositoryException(" Cannot perform your command. Make sure you use !reset first.");
        }
    }

    private City updateCommodities(City city, String ocrCommodities) {

        String[] ocrCommoditiesInLines = ocrCommodities.split("\n");

        for (int i = 0; i < ocrCommoditiesInLines.length; i++) {

            for (CommodityName commodityName : CommodityName.values()) {
                Pattern compiledPatternCommodity = Pattern.compile(commodityName.getAbbrev().toLowerCase());
                if (compiledPatternCommodity.matcher(ocrCommoditiesInLines[i].toLowerCase()).find()) {
                    Commodity commodity = city.getCommodityByName(commodityName);
                    setPriceBasedOnOcr(commodity, ocrCommoditiesInLines[i + 1]);
                }
            }

        }

        return city;
    }

    private void setPriceBasedOnOcr(Commodity commodity, String ocrLineWithPrice) {

        String[] ocrLineWithPriceInLines = ocrLineWithPrice.split("\\s+");

        for (int i = 0; i < ocrLineWithPriceInLines.length; i++) {
            Pattern compiledPatternPrice = Pattern.compile("Pric");
            if (compiledPatternPrice.matcher(ocrLineWithPriceInLines[i]).find()) {
                int price = ConvertStringToInt(ocrLineWithPriceInLines[i + 1]);
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