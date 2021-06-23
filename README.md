### NILE REST API SERVICE APPLICATION
- SPRING BOOT
  - DEVTOOLS
  - JPA
- SWAGGER
- LOMBOK
- QUERYDSL
- FLYWAY
- HATEOAS

https://jojoldu.tistory.com/372
https://velog.io/@recordsbeat/QueryDSL%EB%A1%9C-%EA%B2%80%EC%83%89-%ED%8E%98%EC%9D%B4%EC%A7%95-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0
https://jessyt.tistory.com/55
https://victorydntmd.tistory.com/206
https://velog.io/@junho918/Spring-Data-Jpa-JPA..%EA%B7%B8%EB%9E%98-%EC%95%8C%EA%B2%A0%EC%96%B4..-%EA%B7%B8%EB%9E%98%EC%84%9C-%EC%8A%A4%ED%94%84%EB%A7%81-%EB%8D%B0%EC%9D%B4%ED%84%B0-JPA%EB%8A%94-%EC%96%B4%EB%96%BB%EA%B2%8C-%EC%93%B0%EB%8A%94%EB%8D%B0
google-search: jpa querydsl procedure pagination
https://freedeveloper.tistory.com/28
https://velog.io/@junho918/Querydsl-%EC%8B%A4%EC%A0%84-Querydsl
google-search: jpa page entity change

## 읽어 볼 것
https://jojoldu.tistory.com/165?category=637935
https://kkambi.tistory.com/193

https://www.youtube.com/watch?v=ePCeuiIJbBI
https://www.youtube.com/watch?v=HgB90XUmpeE
https://www.youtube.com/watch?v=wJBAFZv_KN0
https://www.youtube.com/watch?v=B-IHz3oVUG8
https://www.youtube.com/watch?v=Xbk9z7oarYU
https://www.youtube.com/watch?v=N7nLUQMmjxs
https://www.youtube.com/watch?v=w6XoZ3Luzus
https://www.youtube.com/watch?v=hmSPJHtZyp4

google-search: jpa criteria api vs querydsl
https://velog.io/@neity16/4-%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B6%80%ED%8A%B8%EC%99%80-JPA-%ED%99%9C%EC%9A%A9-4-%EB%8F%99%EC%A0%81-%EC%BF%BC%EB%A6%AC-JPQL-vs-Criteria-vs-QueryDSL
https://www.baeldung.com/intro-to-querydsl
https://kimyhcj.tistory.com/356
https://wwlee94.github.io/category/study/jpa-programming/chapter6-object-oriented-query-language/
https://stackoverflow.com/questions/53325506/criteria-api-vs-querydsl-vs-jpa-metamodel
https://webcoding-start.tistory.com/60
https://kohen.tistory.com/4
google-search: jpa querydsl pageable
google-search: JPA Specification

#### 우선
인증, 토큰... 제외 상태

# 배포
1. CentOs 에서 jar 구동
2. cors 설정.. 현재는 local에서 내부 호출만 할 것이기 때문에 cors 설정 안해도 상관없는지
  - 없다면 안해도 됨 (당분간은 local 호출만 할 것임)


#### 해야 할 것
- jpa paging (pageable, page 사용)
- exception
- HATEOAS
- jwt 토큰
- 인증(사용자권한)

#### 추가로해야 할 것
- mapping annotation @operation responses not apply
- enum class 적용 (noti type)
- 공통코드 테이블 entity 생성
- config 테이블 entity 생성
- multiple primary key entity
- cud 될 때 response 에 대한 공통 클래스 고려
- @Query, @Procedure 에서 schema 선택하기
- sql 실행(jpa)
- flyway query script 실행 순서 조정

#### 알아봐야 할 것
- @PathVariable, @RequestBody, @RequestParam
- Datetime 검색 (orElse 쓰는방법으로)

#### 관련기능
noti
asset
result

#### 구성
- model
  - controller
  - service
  - repository
  - entity
    - enums
  - dto
    - request
    - response
  - exception
    - exceptions
