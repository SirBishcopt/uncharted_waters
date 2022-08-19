package com.sirbishcopt.unchartedwaters.controller;

import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.Inventory;
import com.sirbishcopt.unchartedwaters.service.NextCityService;
import org.springframework.stereotype.Controller;

@Controller
public class NextCityController {

    private NextCityService nextCityService;

    public NextCityController(NextCityService nextCityService) {
        this.nextCityService = nextCityService;
    }

    public CityName getNameOfNextCity(String message, boolean isLastTransaction) {
        Inventory inventory = nextCityService.createInventoryFromMessage(message);
        return nextCityService.getNextCity(inventory, isLastTransaction);
    }

}