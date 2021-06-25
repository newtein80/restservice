package com.nile.apiservice.member.repository;

import com.nile.apiservice.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{
    Member findByUsername(String username);
}
