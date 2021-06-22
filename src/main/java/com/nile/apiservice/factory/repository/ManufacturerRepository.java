package com.nile.apiservice.factory.repository;

import com.nile.apiservice.factory.model.entity.Manufacturer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {
    
}
