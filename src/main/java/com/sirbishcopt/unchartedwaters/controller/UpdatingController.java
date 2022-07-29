package com.sirbishcopt.unchartedwaters.controller;

import com.sirbishcopt.unchartedwaters.domain.CityName;
import org.springframework.stereotype.Controller;

import java.util.regex.Pattern;

@Controller
public class UpdatingController {

    public CityName getCityName(String content) {
        for (CityName cityName : CityName.values()) {
            Pattern compiledPattern = Pattern.compile(cityName.getAbbrev().toLowerCase());
            if (compiledPattern.matcher(content.toLowerCase()).find()) {
                return cityName;
            }
        }
        // TODO throw exception
        return null;
    }

    public void updateCity(CityName cityName, String[] attachments) {
        // TODO finish method
    }

}