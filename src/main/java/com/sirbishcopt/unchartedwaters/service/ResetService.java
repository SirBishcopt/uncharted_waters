package com.sirbishcopt.unchartedwaters.service;

import com.sirbishcopt.unchartedwaters.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class ResetService {

    private CityRepository cityRepository;

    public ResetService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void resetDatabase() {
        cityRepository.clearTable();
    }

}