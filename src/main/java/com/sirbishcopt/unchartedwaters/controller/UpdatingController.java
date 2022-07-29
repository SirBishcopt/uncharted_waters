package com.sirbishcopt.unchartedwaters.controller;

import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.service.UpdatingService;
import org.springframework.stereotype.Controller;

@Controller
public class UpdatingController {

    private UpdatingService updatingService;

    public UpdatingController(UpdatingService updatingService) {
        this.updatingService = updatingService;
    }

    public CityName getCityName(String message) {
        return updatingService.getCityNameFromMessage(message);
    }

    public void updateCity(CityName cityName, String[] attachments) {
        updatingService.updateCity(cityName, attachments);
    }

    public void markCityAsEmpty(CityName cityName) {
        updatingService.markCityAsEmpty(cityName);
    }

}