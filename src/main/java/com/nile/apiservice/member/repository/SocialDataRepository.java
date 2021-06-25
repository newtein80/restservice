package com.nile.apiservice.member.repository;

import com.nile.apiservice.member.entity.SocialData;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialDataRepository extends CrudRepository<SocialData,Long> {

    SocialData findByIdAndType(String username, String type);

}