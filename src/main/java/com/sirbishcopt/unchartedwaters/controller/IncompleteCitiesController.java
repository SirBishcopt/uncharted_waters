package com.sirbishcopt.unchartedwaters.controller;

import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.service.IncompleteCitiesService;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class IncompleteCitiesController {

    private IncompleteCitiesService incompleteCitiesService;

    public IncompleteCitiesController(IncompleteCitiesService incompleteCitiesService) {
        this.incompleteCitiesService = incompleteCitiesService;
    }

    public Map<CityName, Integer> getIncompleteCities() {
        return incompleteCitiesService.listNamesOfIncompleteCitiesAndAmountOfLackingCommodities();
    }

}