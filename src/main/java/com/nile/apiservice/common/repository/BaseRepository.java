package com.nile.apiservice.common.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    Page<T> findAll(Pageable pageable, Integer totalItems);
    Page<T> findAll(Specification<T> spec, Pageable pageable, Integer totalItems);
    Page<T> findAll(Specification<T> spec, Pageable pageable, Integer totalItems, Sort sort);
}
