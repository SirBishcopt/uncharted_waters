package com.sirbishcopt.unchartedwaters.repository;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeaderRepository {

    private final CityRepository cityRepository;
    private final CommodityRepository commodityRepository;

    public LeaderRepository(CityRepository cityRepository, CommodityRepository commodityRepository) {
        this.cityRepository = cityRepository;
        this.commodityRepository = commodityRepository;
    }

    public City getCityByName(CityName cityName) {
        return cityRepository.findById(cityName).get();
    }

    public void resetTable() {
        if (cityRepository.findAll().isEmpty()) {
            resetTableIfRepositoryIsEmpty();
        } else {
            resetTableIfRepositoryIsNotEmpty();
        }
    }

    public void save(City city) {
        commodityRepository.saveAll(city.getCommodities());
        cityRepository.save(city);
    }

    public void markCityAsEmpty(CityName cityName) {
        City city = getCityByName(cityName);
        city.setEmpty(true);
        save(city);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public List<City> getCitiesThatAreNotEmpty() {
        return cityRepository.findByEmptyFalse();
    }

    private void resetTableIfRepositoryIsEmpty() {
        CityName.stream()
                .map(City::new)
                .forEach(this::save);
    }

    private void resetTableIfRepositoryIsNotEmpty() {
        CityName.stream()
                .map(this::getCityByName)
                .peek(city -> city.setEmpty(false))
                .peek(city -> CommodityName.stream()
                        .map(city::getCommodityByName)
                        .forEach(commodity -> commodity.setPrice(0)))
                .forEach(this::save);
    }

}