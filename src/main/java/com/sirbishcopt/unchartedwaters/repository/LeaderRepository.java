package com.sirbishcopt.unchartedwaters.repository;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import org.springframework.stereotype.Repository;

@Repository
public class LeaderRepository {

    private CityRepository cityRepository;
    private CommodityRepository commodityRepository;

    public LeaderRepository(CityRepository cityRepository, CommodityRepository commodityRepository) {
        this.cityRepository = cityRepository;
        this.commodityRepository = commodityRepository;
    }

    public City getCityByName(CityName cityName) {
        return cityRepository.findById(cityName).get();
    }

    public void resetTable() {
        cityRepository.deleteAll();
        for (CityName cityName : CityName.values()) {
            City city = new City(cityName);
            commodityRepository.saveAll(city.getCommodities());
            cityRepository.save(city);
        }
    }

    public void save(City city) {
        commodityRepository.saveAll(city.getCommodities());
        cityRepository.save(city);
    }

    // TODO consider doing it with annotations in CityRepository
    public void markCityAsEmpty(CityName cityName) {
        //cityRepository.markCityAsEmpty(cityName);
        City city = getCityByName(cityName);
        city.markAsEmpty();
        save(city);
    }

}