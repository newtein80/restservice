package com.nile.apiservice.factory.repository;

import com.nile.apiservice.factory.model.entity.Country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>{
    Country findById(int id);
}
