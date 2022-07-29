package com.sirbishcopt.unchartedwaters.controller;

import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.service.CitiesNeedingUpdateService;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class CitiesNeedingUpdateController {

    private CitiesNeedingUpdateService citiesNeedingUpdateService;

    public CitiesNeedingUpdateController(CitiesNeedingUpdateService citiesNeedingUpdateService) {
        this.citiesNeedingUpdateService = citiesNeedingUpdateService;
    }

    public String getCitiesNeedingUpdate() {
        Map<CityName, Integer> citiesNeedingUpdate = citiesNeedingUpdateService.listNamesOfCitiesAndAmountOfCommoditiesThatRequireUpdate();
        String citiesAndCommodities = null;
        for (Map.Entry<CityName, Integer> cityNameIntegerEntry : citiesNeedingUpdate.entrySet()) {
            if (cityNameIntegerEntry.getValue() != 0) {
                citiesAndCommodities = citiesAndCommodities + "\n:small_blue_diamond: " + cityNameIntegerEntry.getKey().toString()
                        + " (lacking " + cityNameIntegerEntry.getValue() + " commodities)";
            }
        }
        if (citiesAndCommodities != null) {
            return "All hands on deck, Captains! We still need to update:" + citiesAndCommodities;
        } else {
            return "We're good, Captains :grin: Every city has been checked.";
        }
    }

}