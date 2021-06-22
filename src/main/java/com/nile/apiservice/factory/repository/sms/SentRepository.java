package com.nile.apiservice.factory.repository.sms;

import com.nile.apiservice.factory.model.entity.sms.Sent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SentRepository extends JpaRepository<Sent, Integer> {
    
}
