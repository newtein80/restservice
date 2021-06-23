package com.nile.apiservice.noti.repository;

import java.util.List;

import com.nile.apiservice.noti.entity.Noti;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import static com.nile.apiservice.noti.entity.QNoti.noti;

/**
 * https://jojoldu.tistory.com/372
 */
@Repository
@RequiredArgsConstructor
public class OnlyQuerydslNotiRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<Noti> onlyquerydslFindByNotiTitle(String noti_title) {
        return jpaQueryFactory.selectFrom(noti).where(noti.notititle.containsIgnoreCase(noti_title)).fetch();
    }
}
