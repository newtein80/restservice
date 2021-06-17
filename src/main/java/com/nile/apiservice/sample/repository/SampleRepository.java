package com.nile.apiservice.sample.repository;

import com.nile.apiservice.sample.model.entity.Sample;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long>{
    Sample findBySampleTitle(String sampleTitle);
}
