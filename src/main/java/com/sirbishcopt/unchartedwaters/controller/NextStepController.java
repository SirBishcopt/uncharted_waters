package com.sirbishcopt.unchartedwaters.controller;

import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.Inventory;
import com.sirbishcopt.unchartedwaters.service.NextStepService;
import org.springframework.stereotype.Controller;

@Controller
public class NextStepController {

    private NextStepService nextStepService;

    public NextStepController(NextStepService nextStepService) {
        this.nextStepService = nextStepService;
    }

    public CityName getNameOfNextCity(String message, boolean isLastTransaction) {
        Inventory inventory = nextStepService.createInventoryFromMessage(message);
        return nextStepService.getNextStep(inventory, isLastTransaction);
    }

}