package com.sirbishcopt.unchartedwaters.repository;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, CityName> {

    List<City> findByEmptyFalse();

}