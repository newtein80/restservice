package com.nile.apiservice.factory.repository;

import com.nile.apiservice.factory.model.entity.City;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{
    City findByCityname(String cityname);
}
