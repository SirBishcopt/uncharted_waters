package com.sirbishcopt.unchartedwaters.controller;

import com.sirbishcopt.unchartedwaters.service.ResetService;
import org.springframework.stereotype.Controller;

@Controller
public class ResetController {

    private ResetService resetService;

    public ResetController(ResetService resetService) {
        this.resetService = resetService;
    }

    public void resetDatabase() {
        resetService.resetDatabase();
    }

}