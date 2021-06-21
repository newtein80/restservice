package com.nile.apiservice.sample.repository;

import java.util.List;

import com.nile.apiservice.sample.model.entity.Sample;

public interface CustomSampleRepository {
    public List<Sample> findSampleSearchTitleAndContent(String sampleTitle, String sampleContent);
}
