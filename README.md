### NILE REST API SERVICE APPLICATION
- SPRING BOOT
  - DEVTOOLS
  - JPA
- SWAGGER
- LOMBOK
- QUERYDSL
- FLYWAY
- HATEOAS

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
