package com.nile.apiservice.noti.repository;

import java.util.List;

import com.nile.apiservice.noti.entity.Noti;
import com.nile.apiservice.noti.entity.QNoti;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.nile.apiservice.noti.entity.QNoti.noti;

@Repository
public class QuerydslNotiRepository extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public QuerydslNotiRepository(JPAQueryFactory queryFactory) {
        super(Noti.class);
        this.queryFactory = queryFactory;
    }

    public List<Noti> querydslFindByNotititle(String noti_title) {
        // ! 중요: mvn clean compile -DskipTests
        // ! https://sinau.tistory.com/44
        return queryFactory.selectFrom(noti).where(noti.notititle.eq(noti_title)).fetch();
    }

}
