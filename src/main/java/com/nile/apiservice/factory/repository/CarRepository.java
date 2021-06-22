package com.nile.apiservice.factory.repository;

import com.nile.apiservice.factory.model.entity.vehicle.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    
}
