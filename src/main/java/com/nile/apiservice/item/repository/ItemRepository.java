package com.nile.apiservice.item.repository;

import com.nile.apiservice.item.model.entity.Item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>{
    Page<Item> findByTitle(String title, Pageable pageable);
    Page<Item> findByAllCustom(Pageable pageable);
}