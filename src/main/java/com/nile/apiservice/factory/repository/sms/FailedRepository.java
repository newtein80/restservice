package com.nile.apiservice.factory.repository.sms;

import com.nile.apiservice.factory.model.entity.sms.Failed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FailedRepository extends JpaRepository<Failed, Integer> {
    
}
