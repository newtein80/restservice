package com.nile.apiservice.noti.repository;

import java.util.Date;
import java.util.List;

import com.nile.apiservice.noti.entity.Noti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotiRepository extends JpaRepository<Noti, Long>, CustomNotiRepository{
    List<Noti> findByCreatedtBetween(Date startdate, Date enddate);
    List<Noti> findByCreatedtAfter(Date startdate);
    List<Noti> findByCreatedtBefore(Date enddate);
}
