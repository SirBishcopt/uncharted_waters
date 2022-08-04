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
        String citiesAndCommodities = "";
        for (Map.Entry<CityName, Integer> cityNameIntegerEntry : citiesNeedingUpdate.entrySet()) {
            citiesAndCommodities = citiesAndCommodities + "\n- " + cityNameIntegerEntry.getKey().toString()
                    + " (lacking " + cityNameIntegerEntry.getValue() + " commodities)";
        }

        if (citiesAndCommodities.isEmpty()) {
            return "We're good, Captains :grin: Every city has been checked.";
        } else {
            return "All hands on deck, Captains! We still need to update:" + citiesAndCommodities;
        }
    }

}