package com.sirbishcopt.unchartedwaters.service.collecting;

import com.sirbishcopt.unchartedwaters.domain.City;

public interface StringToCityUpdater {

    // TODO consider whether it sould return void or City
    void updateCityBasedOnString(String ocrName, String ocrCommodities1, String ocrCommodities2);

}