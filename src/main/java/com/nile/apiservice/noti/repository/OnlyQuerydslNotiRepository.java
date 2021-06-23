package com.nile.apiservice.noti.repository;

import java.util.List;
import java.util.Optional;

import com.nile.apiservice.noti.entity.Noti;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import static com.nile.apiservice.noti.entity.QNoti.noti;

/**
 * https://jojoldu.tistory.com/372
 * https://kkambi.tistory.com/193
 */
@Repository
@RequiredArgsConstructor
public class OnlyQuerydslNotiRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<Noti> onlyquerydslFindByNotiTitle(String noti_title) {
        return jpaQueryFactory.selectFrom(noti).where(noti.notititle.containsIgnoreCase(noti_title)).fetch();
    }

    public Optional<Noti> onlyquerydslFindByNotiId(Long noti_id) {
        return Optional.ofNullable(
            jpaQueryFactory.selectFrom(noti).where(noti.id.eq(noti_id)).fetchOne()
        );
    }

    public List<Noti> onlyquerydslFindByTitleOrBody(String title, String body) {
        // todo: BooleanBuilder 의 사용 방법
        BooleanBuilder builder = new BooleanBuilder();

        if (!StringUtils.isEmpty(title)) {
            builder.and(noti.notititle.containsIgnoreCase(title));
        }

        if (!StringUtils.isEmpty(body)) {
            builder.and(noti.notibody.containsIgnoreCase(body));
        }

        return jpaQueryFactory.selectFrom(noti).where(builder).fetch();
    }
}
