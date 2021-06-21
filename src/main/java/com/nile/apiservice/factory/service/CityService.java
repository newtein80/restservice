package com.nile.apiservice.factory.service;

import java.util.List;

import com.nile.apiservice.factory.model.entity.City;
import com.nile.apiservice.factory.repository.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    
    @Autowired CityRepository cityRepository;

    public List<City> getCities() {
        return this.cityRepository.findAll();
    }

    public City saveCity(City cityDto) {
        return this.cityRepository.save(cityDto);
    }

    public City getCityByName(String cityname) {
        return this.cityRepository.findByCityname(cityname);
    }
}
