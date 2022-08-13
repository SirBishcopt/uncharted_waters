package com.sirbishcopt.unchartedwaters.repository;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CityRepository extends JpaRepository<City, CityName> {

    //@Query("SELECT c FROM City c JOIN FETCH c.commodities WHERE c.cityName = :cityName")
    //City getCityByName(@Param("cityName") CityName cityName);

    // TODO is this approach better?
    // Commodity getCommodityByNameFromSpecificCity(CityName cityName, CommodityName commodityName);

    // TODO is this approach better?
    // List<City> getCitiesThatAreNotEmpty();

    @Modifying(clearAutomatically = true)
    @Query("update City c set c.isEmpty = TRUE where c.cityName = :cityName")
    void markCityAsEmpty(@Param("cityName") CityName cityName);

}