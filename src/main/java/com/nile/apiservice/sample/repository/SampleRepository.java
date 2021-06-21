package com.nile.apiservice.sample.repository;

import com.nile.apiservice.sample.model.entity.Sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Long>, CustomSampleRepository, SampleCustomSearchRepository{

}
