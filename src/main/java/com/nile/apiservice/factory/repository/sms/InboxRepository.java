package com.nile.apiservice.factory.repository.sms;

import com.nile.apiservice.factory.model.entity.sms.Inbox;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InboxRepository extends JpaRepository<Inbox, Integer>{
    
}
