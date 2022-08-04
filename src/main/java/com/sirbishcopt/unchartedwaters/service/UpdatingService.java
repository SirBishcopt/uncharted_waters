package com.sirbishcopt.unchartedwaters.service;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.Commodity;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;
import com.sirbishcopt.unchartedwaters.repository.LeaderRepository;
import com.sirbishcopt.unchartedwaters.service.ocr.ImageManipulationService;
import com.sirbishcopt.unchartedwaters.service.ocr.OcrService;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.regex.Pattern;

@Service
public class UpdatingService {

    LeaderRepository leaderRepository;
    ImageManipulationService imageManipulationService;
    OcrService ocrService;

    public UpdatingService(LeaderRepository leaderRepository, ImageManipulationService imageManipulationService, OcrService ocrService) {
        this.leaderRepository = leaderRepository;
        this.imageManipulationService = imageManipulationService;
        this.ocrService = ocrService;
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
        City city = leaderRepository.getCityByName(cityName);
        BufferedImage bufferedImage1 = imageManipulationService.prepareImage(attachments[0], false);
        String ocrCommodities1 = ocrService.doOcr(bufferedImage1);
        addCommodities(city, ocrCommodities1);
        BufferedImage bufferedImage2 = imageManipulationService.prepareImage(attachments[0], true);
        String ocrCommodities2 = ocrService.doOcr(bufferedImage2);
        addCommodities(city, ocrCommodities2);
        BufferedImage bufferedImage3 = imageManipulationService.prepareImage(attachments[1], false);
        String ocrCommodities3 = ocrService.doOcr(bufferedImage3);
        addCommodities(city, ocrCommodities3);
        BufferedImage bufferedImage4 = imageManipulationService.prepareImage(attachments[1], true);
        String ocrCommodities4 = ocrService.doOcr(bufferedImage4);
        addCommodities(city, ocrCommodities4);
        System.out.println(ocrCommodities1);
        System.out.println(ocrCommodities2);
        System.out.println(ocrCommodities3);
        System.out.println(ocrCommodities4);
        leaderRepository.save(city);
    }

    private void addCommodities(City city, String ocrCommodities) {
        String[] lines = ocrCommodities.split("\n");
        for (int i = 0; i < lines.length; i++) {
            for (CommodityName commodityName : CommodityName.values()) {
                Pattern compiledPatternCommodity = Pattern.compile(commodityName.getAbbrev().toLowerCase());
                if (compiledPatternCommodity.matcher(lines[i].toLowerCase()).find()) {
                    Commodity commodity = city.getCommodityByName(commodityName);
                    // TODO what if i+1 does not exist
                    String nextLine = lines[i + 1];
                    String[] nextLineCut = nextLine.split("\\s+");
                    int price = 0;
                    for (int k = 0; k < nextLineCut.length; k++) {
                        String subLine = nextLineCut[k];
                        Pattern compiledPatternPrice = Pattern.compile("Pric");
                        if (compiledPatternPrice.matcher(subLine).find()) {
                            // TODO what if k+1 does not exist
                            String priceInString = nextLineCut[k + 1];
                            Pattern compiledPatternThousand = Pattern.compile("k");
                            if (compiledPatternThousand.matcher(priceInString.toLowerCase()).find()) {
                                priceInString = priceInString.replaceAll("[.]", "").replaceAll("[kK]", "");
                            }
                            price = Integer.parseInt(priceInString);
                            commodity.setPrice(price);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void markCityAsEmpty(CityName cityName) {
        leaderRepository.markCityAsEmpty(cityName);
    }

}