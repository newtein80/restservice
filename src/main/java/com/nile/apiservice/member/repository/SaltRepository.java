package com.nile.apiservice.member.repository;

import com.nile.apiservice.member.encryt.Salt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SaltRepository extends JpaRepository<Salt,Long> {
    
}
