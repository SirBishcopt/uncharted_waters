package com.sirbishcopt.unchartedwaters.repository;

import com.sirbishcopt.unchartedwaters.domain.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommodityRepository extends JpaRepository<Commodity,Integer> {

}