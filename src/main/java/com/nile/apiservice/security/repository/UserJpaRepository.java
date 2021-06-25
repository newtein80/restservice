package com.nile.apiservice.security.repository;

import java.util.Optional;

import com.nile.apiservice.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByUid(String email);

    Optional<User> findByUidAndProvider(String uid, String provider);
}