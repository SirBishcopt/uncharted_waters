package com.sirbishcopt.unchartedwaters.service;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.Commodity;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;
import com.sirbishcopt.unchartedwaters.repository.CityRepository;
import com.sirbishcopt.unchartedwaters.service.ocr.ImageManipulationService;
import com.sirbishcopt.unchartedwaters.service.ocr.OcrService;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.regex.Pattern;

@Service
public class UpdatingService {

    CityRepository cityRepository;
    ImageManipulationService imageManipulationService;
    OcrService ocrService;

    public UpdatingService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public CityName getCityNameFromMessage(String message) {
        // TODO throw exception if CityName not found
        for (CityName cityName : CityName.values()) {
            Pattern compiledPattern = Pattern.compile(cityName.getAbbrev().toLowerCase());
            if (compiledPattern.matcher(message.toLowerCase()).find()) {
                return cityName;
            }
        }
        return null;
    }

    public void updateCity(CityName cityName, String[] attachments) {
        City city = cityRepository.getReferenceById(cityName);
        BufferedImage bufferedImage1 = imageManipulationService.prepareImage(attachments[0], false);
        String ocrCommodities1 = ocrService.doOcr(bufferedImage1);
        updateCommodities(city, ocrCommodities1);
        BufferedImage bufferedImage2 = imageManipulationService.prepareImage(attachments[0], true);
        String ocrCommodities2 = ocrService.doOcr(bufferedImage2);
        updateCommodities(city, ocrCommodities2);
        BufferedImage bufferedImage3 = imageManipulationService.prepareImage(attachments[1], false);
        String ocrCommodities3 = ocrService.doOcr(bufferedImage3);
        updateCommodities(city, ocrCommodities3);
        BufferedImage bufferedImage4 = imageManipulationService.prepareImage(attachments[1], true);
        String ocrCommodities4 = ocrService.doOcr(bufferedImage4);
        updateCommodities(city, ocrCommodities4);
        cityRepository.save(city);
    }

    private void updateCommodities(City city, String ocrCommodities) {
        String[] lines = ocrCommodities.split("/n");
        for (int i = 0; i < lines.length; i++) {
            for (CommodityName commodityName : CommodityName.values()) {
                Pattern compiledPatternCommodity = Pattern.compile(commodityName.getAbbrev().toLowerCase());
                if (compiledPatternCommodity.matcher(lines[i].toLowerCase()).find()) {
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
                            Pattern compiledPatternThousand = Pattern.compile("k");
                            if (compiledPatternThousand.matcher(priceInString.toLowerCase()).find()) {
                                priceInString.replaceAll("[kK]", "");
                                double priceInDouble = Double.parseDouble(priceInString);
                                priceInDouble *= 1000;
                                price = (int) priceInDouble;
                            } else {
                                price = Integer.parseInt(priceInString);
                            }
                        }
                    }
                    commodity.setPrice(price);
                }
            }
        }
    }

    public void markCityAsEmpty(CityName cityName) {
        cityRepository.markCityAsEmpty(cityName);
    }

}