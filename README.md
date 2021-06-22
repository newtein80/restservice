### NILE REST API SERVICE APPLICATION
- SPRING BOOT
  - DEVTOOLS
  - JPA
- SWAGGER
- LOMBOK
- QUERYDSL
- FLYWAY
- HATEOAS

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

#### 해야 할 것
- jpa paging (pageable, page 사용)
- exception
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
