package com.nile.apiservice.member.repository;

import java.util.Optional;

import com.nile.apiservice.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{
    Member findByUsername(String username);
    Optional<Member> findByEmail(String email);
}
