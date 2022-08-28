package com.sirbishcopt.unchartedwaters.utils;

import com.sirbishcopt.unchartedwaters.domain.CityName;

import java.util.random.RandomGenerator;

public final class RandomUtil {

    public static CityName drawRandomCityNameOfTwo(CityName cityName1, CityName cityName2) {
        RandomGenerator random = RandomGenerator.getDefault();
        double randomDouble = random.nextDouble();
        if (randomDouble < 0.5) {
            return cityName1;
        } else {
            return cityName2;
        }
    }

}