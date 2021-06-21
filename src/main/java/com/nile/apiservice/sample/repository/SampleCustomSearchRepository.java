package com.nile.apiservice.sample.repository;

import java.util.List;

import com.nile.apiservice.sample.model.entity.Sample;

import org.springframework.data.jpa.repository.Query;

public interface SampleCustomSearchRepository {
    List<Sample> getMultipleItemSearchSample(String searchItem);

    @Query(value = "select rest.gettotaldetailcount()", nativeQuery = true)
    int gettotaldetailcount();
}