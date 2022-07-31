package com.sirbishcopt.unchartedwaters.repository;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.Commodity;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, CityName> {

    //@Query("SELECT c FROM City c WHERE c.cityName = :cityName")
    //City getCityByName(@Param("cityName") CityName cityName);
    //getReferenceById

    // TODO is this approach better?
    // Commodity getCommodityByNameFromSpecificCity(CityName cityName, CommodityName commodityName);

    // TODO is this approach better?
    // List<City> getCitiesThatAreNotEmpty();

    @Modifying
    @Query("update City c set c.isEmpty = TRUE where c.name = :name")
    void markCityAsEmpty(@Param("name") CityName cityName);

    default void clearTable(){
        this.deleteAll();
        for (CityName cityName : CityName.values()) {
            City city = new City(cityName);
            this.save(city);
        }
        // TODO finish method
    }

}