package com.nile.apiservice.noti.repository;

import com.nile.apiservice.noti.entity.Noti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotiRepository extends JpaRepository<Noti, Long>{
    
}
