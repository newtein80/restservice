package com.nile.apiservice.common.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// @Repository
// @Transactional
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public Page<T> findAll(Pageable pageable, Integer totalItems) {
        return findAll(null, pageable, totalItems, Sort.unsorted());
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable, Integer totalItems) {
        return findAll(spec, pageable, totalItems, Sort.unsorted());
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable, Integer totalItems, Sort sort) {
        TypedQuery<T> query = getQuery(spec, sort);

        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();

        if(totalItems == null){
            return findAll(spec, pageable);
        }

        if(pageNumber < 0){
            throw new IllegalArgumentException("page number must not be less than zero");
        }

        if(pageSize < 1){
            throw new IllegalArgumentException("pageSize must not be less than one");
        }

        if(totalItems < pageNumber * pageSize){
            throw new IllegalArgumentException("totalItems must not be less than pageNumber * pageSize");
        }

        int offset = (int) pageable.getOffset();
        query.setFirstResult(offset);
        query.setMaxResults(pageable.getPageSize());

        return new PageImpl<>(query.getResultList(), PageRequest.of(pageNumber, pageSize), totalItems);
    }
}