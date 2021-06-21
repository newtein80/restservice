package com.nile.apiservice.factory.repository;

import com.nile.apiservice.factory.model.entity.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
    
    public Course findById(int id);
}
