package com.nile.apiservice.factory.repository;

import com.nile.apiservice.factory.model.entity.CourseInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseInfoRepository extends JpaRepository<CourseInfo, Integer>{
    public CourseInfo findById(int id);
}
