package com.nile.apiservice.noti.repository;

import java.util.List;
import java.util.Optional;

import com.nile.apiservice.noti.entity.Noti;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    // todo: Page<Noti> 를 나중에 Page<NotiDto>로 어떻게 변경해야 하는가???
    // ! https://parkadd.tistory.com/17
    // ! https://joont92.github.io/jpa/Spring-Data-JPA/
    public Page<Noti> onlyquerydslFindByTitleOrBodyWithPaging(String title, String body, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        if (!StringUtils.isEmpty(title)) {
            builder.and(noti.notititle.containsIgnoreCase(title));
        }

        if (!StringUtils.isEmpty(body)) {
            builder.and(noti.notibody.containsIgnoreCase(body));
        }

        QueryResults<Noti> queryResults = jpaQueryFactory
        .selectFrom(noti)
        .where(
            // builder
            containTitle(title), containBody(body)
        )
        .orderBy(noti.createdt.desc(), noti.notititle.asc())
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetchResults();

        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    // todo: 검색조건이 생길때마다 BooleanExpression 반환 메소드는 계속 생겨야 하는가???
    private BooleanExpression containTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            // where 절에 null이 들어가면 해당 조건은 자동으로 미사용
            // 단 모든 조건이 null이 되버리면 where절이 없어지면서 모든 row를 조회하기 때문에 주의해야 합니다.
            // 일반적으로는 컨트롤러 단에서 validation을 하면서 파라미터가 없는 경우를 방지하기 때문에 쉽게 해결
            return null;
        }
        
        return noti.notititle.containsIgnoreCase(title);
    }

    private BooleanExpression containBody(String body) {
        if (StringUtils.isEmpty(body)) {
            return null;
        }
        
        return noti.notibody.containsIgnoreCase(body);
    }
}
