package com.sirbishcopt.unchartedwaters.repository;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.Commodity;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;
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

    // TODO consider doing with deleteAll()
    public void resetTable() {
        if (cityRepository.findAll().isEmpty()) {
            for (CityName cityName : CityName.values()) {
                City city = new City(cityName);
                save(city);
            }
        } else {
            for (CityName cityName : CityName.values()) {
                City city = getCityByName(cityName);
                city.setEmpty(false);
                for (CommodityName commodityName : CommodityName.values()) {
                    Commodity commodity = city.getCommodityByName(commodityName);
                    commodity.setPrice(0);
                }
                save(city);
            }
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
        city.setEmpty(true);
        save(city);
    }

}