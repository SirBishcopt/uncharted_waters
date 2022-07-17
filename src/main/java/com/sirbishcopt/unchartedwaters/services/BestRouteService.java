package com.sirbishcopt.unchartedwaters.services;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;

import java.util.List;

public class BestRouteService {

    public List<City> getBestRoute(List<City> cities, City firstCity, int lengthOfRoute){
        CommodityName cheapestCommodityName = firstCity.getCheapestCommodity().getName();
        List<City> citiesOfBestRoute;
        //return citiesOfBestRoute;
        return null;
    }

}
