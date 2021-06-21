package com.nile.apiservice.factory.service;

import java.util.List;
import java.util.Set;

import com.nile.apiservice.factory.model.dto.CityRequest;
import com.nile.apiservice.factory.model.entity.City;
import com.nile.apiservice.factory.model.entity.Country;
import com.nile.apiservice.factory.repository.CityRepository;
import com.nile.apiservice.factory.repository.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    
    @Autowired CityRepository cityRepository;
    @Autowired CountryRepository countryRepository;

    public List<City> getCities() {
        return this.cityRepository.findAll();
    }

    public City saveCity(City cityDto) {
        return this.cityRepository.save(cityDto);
    }

    public City getCityByName(String cityname) {
        return this.cityRepository.findByCityname(cityname);
    }

    public City addCity(CityRequest cityRequest) {
        Country country = this.countryRepository.findById(cityRequest.country_id);
        City city = new City();
        city.setCityname(cityRequest.cityname);
        city.setCitycode(cityRequest.citycode);
        city.setCountry(country);
        return this.cityRepository.save(city);
    }

    public Country getCountry(int id) {
        return this.countryRepository.getById(id);
    }

    public List<Country> getCountryByName(String countrynameprefix) {
        return this.countryRepository.getByCountryname(countrynameprefix);
    }

    public List<Object[]> getCountryByNameAndPopulation(String prefix, long population) {
        return this.countryRepository.getByCountrynameAndPopulation(prefix, population);
    }

    public List<Object[]> getCountryByNameAndPopulationNative(String prefix, long population) {
        return this.countryRepository.getByCountrynameAndPopulationNative(prefix, population);
    }

    public List<Country> getCountryByIds(Set<Integer> ids) {
        return this.countryRepository.getByIds(ids);
    }
}
