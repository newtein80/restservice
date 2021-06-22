package com.nile.apiservice.factory.repository;

import com.nile.apiservice.factory.model.entity.vehicle.Motorbike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorbikeRepository extends JpaRepository<Motorbike, Integer> {
    
}
