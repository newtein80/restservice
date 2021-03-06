package com.nile.apiservice.sample.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.nile.apiservice.sample.model.entity.Sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomSampleRepositoryImpl implements CustomSampleRepository{

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Sample> findSampleSearchTitleAndContent(String sampleTitle, String sampleContent) {
        return null;
    }
    
}
