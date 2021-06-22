package com.nile.apiservice.factory.repository;

import com.nile.apiservice.factory.model.entity.Sms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends JpaRepository<Sms, Integer>{
    
}
