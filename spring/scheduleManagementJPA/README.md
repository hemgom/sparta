# Project - Schedule Management
부트캠프 `CH 3` 에서 학습한 `Spring` 을 토대로 구현한 `일정 관리 앱` 프로젝트 아래와 같은 목표가 있다.
- `JPA` 의 주요 개념을 이해하고, 애플리케이션에 적용하는 방법 숙달
- `인증 및 인가` 에 대한 개념을 이해하고, `JWT` 을 사용해 애플리케이션의 `보안 및 접근` 을 제어하는 방법 숙달
- `RestTemplate` 을 사용해 `외부 자원` 과 상호작용하는 방법을 숙달
<br/><br/><br/>

# 요구사항
`필수 기능` 에 해당하는 요구사항을 `순차적` 으로 모두 반영 후, `도전 기능` 에 해당하는 요구사항을 `순차적` 으로 반영해 볼 생각이다.

## 공통 조건
- 모든 테이블은 `고유 식별자(ID)` 를 가져야 한다.
- `3 Layer Architecture` 에 따라 각 `Layer` 의 목적에 맞게 개발해야 한다.
- `CRUD(필수 기능)` 은 모두 `Database` 연결 및 `JPA` 를 사용해 개발해야 한다.
  - `JDBC` 와 `Spring Security` 는 사용하지 않는다.
- `인증/인가` 절차는 `JWT(JSON Web Token)` 을 활용해 개발해야 한다.
- `연관관계 매핑(Relationship Mapping)` 은 `양방향 매핑` 으로 한다.
<br/><br/>

## 필수 기능
### Lv.0 - API 명세서 및 ERD 작성
- [ ] : `README.md` 에 `API 명세서` 작성
- [ ] : `README.md` 에 `ERD` 첨부
- [ ] : `schedule.sql` 에 프로젝트에 사용되는 `DB(shcema) 및 Table` 생성 쿼리 작성
<br/>

### Lv.1 - 일정 CRUD
- [x] : `일정` 을 `생성, 조회, 수정, 삭제` 할 수 있어야 한다.
- [x] : `일정` 은 `작성 유저명`, `할 일 제목`, `할 일 내용`, `작성일`, `수정일` 필드를 가져야 한다.
- [x] : `일정` 삭제시 `일정` 에 달린 `댓글` 들도 함께 삭제되어야 한다.
<br/>

### Lv.2 - 댓글 CRUD
- [x] : `댓글` 을 `생성, 조회, 수정, 삭제` 할 수 있어야 한다.
- [x] : `댓글` 은 `댓글 내용`, `작성일`, `수정일`, `작성 유저명` 필드를 가져야 한다.
- [x] : 생성된 `일정` 에 `댓글` 을 남길(작성) 수 있어야 한다.
  - `댓글` 과 `일정` 은 연관 관계를 갖는다.
<br/>

### Lv.3 - 일정 페이징 조회
- [ ] : `일정` 을 조회시 `Spring Data JPA` 에서 제공하는 `Pageable` 인터페이스를 활용해 `페이지네이션` 을 구현해야 한다.
  - `페이지 번호, 페이지 크기` 를 `쿼리 파라미터(query string parameter)` 로 전달해 `요청 항목` 을 나타내야 한다.
  - `기본 페이지 크기` 는 `10` 으로 적용한다.
- [ ] : `일정` 조회시 반환 되어야 할 정보는 `할 일 제목`, `할 일 내용`, `댓글 개수`, `일정 작성일`, `일정 수정일`, `일정 작성 유저명` 이다.
- [ ] : `일정` 조회시 `수정일` 을 기준으로 `내림차순` 정렬한 결과를 반환해야 한다.
<br/>

### Lv.4 - 유저 CRUD
- [ ] : `유저` 를 `생성, 조회, 수정, 삭제` 할 수 있어야 한다.
- [ ] : `유저` 는 `유저명, 이메일, 작성일, 수정일` 필드를 가져야 한다.
- [ ] : `일정` 은 `작성 유저명` 필드 대신 `유저` 의 `고유 식별자(PK)` 필드를 갖는다.
- [ ] : `일정` 을 작성한 `유저` 는 `일정 담당 유저(들)` 을 설정할 수 있다.
  - `일정 담당 유저(들)` 은 한 마디로 `일정 담당자(들)` 이다.
  - 하나의 `일정`이 여러 `유저` 를 가지게끔 추가된 `설정` 이라 보면 된다.
- [ ] : `일정` 과 `유저` 는 `N:M` 관계를 맺어야 한다.
- [ ] : `JPA 지연로딩` 을 활용해야 한다.
<br/>

### Lv.5 - 다양한 예외처리 적용하기
- [ ] : `validation` 을 활용해 다양한 예외처리를 적용해야 한다.
- [ ] : 구현한 프로젝트를 분석하고 `예외사항` 을 파악해보자.
<br/><br/>

## 도전 기능
<br/><br/><br/>

# API 명세서
## 필수 기능
|   API    | Method |        Request URL         |                            Request Body                             |   HttpStatus   |                                                                         Response Body                                                                         |
|:--------:|:------:|:--------------------------:|:-------------------------------------------------------------------:|:--------------:|:-------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|  일정 생성   |  POST  |         /schedule          |  json { "author" : "작성자 이름", "title" : "일정 제목", "body" : "일정 본문" }  |  201 Created   |                json { "id" : "일정 ID", "author" : "작성자 이름", "title" : "일정 제목", "body" : "일정 본문", "createAt" : "일정 생성일", "updateAt" : "일정 수정일" }                |
|  일정 조회   |  GET   |   /schedule/{scheduleId}   |                                  -                                  |     200 OK     |                json { "id" : "일정 ID", "author" : "작성자 이름", "title" : "일정 제목", "body" : "일정 본문", "createAt" : "일정 생성일", "updateAt" : "일정 수정일" }                |
| 일정 목록 조회 |  GET   | /schedule/search-condition |                                  -                                  |     200 OK     |   json { "scheduleList" : { "id" : "일정 ID", "author" : "작성자 이름", "title" : "일정 제목", "body" : "일정 본문", "createAt" : "일정 생성일", "updateAt" : "일정 수정일" }, ... }   |
|  일정 수정   |  PUT   |   /schedule/{scheduleId}   |            json { "title" : "일정 제목", "body" : "일정 본문" }             | 204 No Content |                                                                               -                                                                               |
|  일정 삭제   | DELETE |   /schedule/{scheduleId}   |                                  -                                  | 204 No Content |                                                                               -                                                                               |
|  댓글 생성   |  POST  |   /comment/{scheduleId}    |            json { body" : "댓글 본문", "author" : "작성자 이름" }            |  201 Created   |             json { "id" : "댓글 ID", "body" : "댓글 본문", "createAt" : "댓글 작성일", "updateAt" : "댓글 수정일", "author" : "작성자 이름", "scheduleId" : "일정 ID" }              |
|  댓글 조회   |  GET   |    /comment/{commentId}    |                                  -                                  |     200 OK     |             json { "id" : "댓글 ID", "body" : "댓글 본문", "createAt" : "댓글 작성일", "updateAt" : "댓글 수정일", "author" : "작성자 이름", "scheduleId" : "일정 ID" }              |
| 댓글 목록 조회 |  GET   | /comment/search-condition  |                                  -                                  |     200 OK     | json { "commentList" : { "id" : "댓글 ID", "body" : "댓글 본문", "createAt" : "댓글 작성일", "updateAt" : "댓글 수정일", "author" : "작성자 이름", "scheduleId" : "일정 ID" }, ... } |
|  댓글 수정   |  PUT   |    /comment/{commentId}    |                      json { "body" : "댓글 본문" }                      | 204 No Content |                                                                               -                                                                               |
|  댓글 삭제   | DELETE |    /comment/{commentId}    |                                  -                                  | 204 No Content |                                                                               -                                                                               |
<br/>

## 도전 기능
<br/><br/>

# ERD
## 필수 기능
### Lv.1
![ERD - Essential Functions(1)](images/ERD%20-%20Essential%20Functions.Level01.png)
<br/>

### Lv.2
![ERD - Essential Functions(2)](images/ERD%20-%20Essential%20Functions.Level02.png)
<br/><br/>

## 도전 기능
<br/><br/><br/>

# SQL
DB 스키마와 해당 스키마의 테이블을 생성하는 `Query` 를 나열한 것, 요구사항에 명시한 대로 `schedule.sql` 도 작성해 두었다.

## 필수 기능
```mysql
# Database(schema) 생성
CREATE DATABASE schedule_management_jpa;

# Lv.1 요구사항 : 'schedule' 테이블 생성
CREATE TABLE schedule (
    id bigint not null auto_increment,
    author varChar(20) not null,
    title varChar(100) not null,
    body varChar(250) not null,
    create_at timestamp not null,
    update_at timestamp not null,
    primary key (id)
);

# Lv.2 요구사항 : 'comment' 테이블 생성
CREATE TABLE comment (
    id bigint not null auto_increment,
    body varChar(150) not null,
    create_at timestamp not null,
    update_at timestamp not null,
    author varChar(20) not null,
    schedule_id bigint not null,
    primary key (id),
    foreign key (schedule_id) references schedule (id)
);
```
<br/><br/>

## 도전 기능
```mysql

```
<br/><br/><br/>

# 구현 및 트러블 슈팅 관련 포스팅
해당 프로젝트 진행간 `구현 및 문제 해결` 에 대한 내용을 다룬 포스팅 목록, 링크를 클릭하면 포스팅을 작성한 개인 블로그로 이동된다.
- [일정 생성, 조회 API 테스트 - 요청 파라미터 검증 문제 파악](https://development-diary-for-me.tistory.com/173)
- [일정 수정, 삭제 API 테스트 - '수정' 기능 수행후 반환 값이 꼭 있어야 할까?](https://development-diary-for-me.tistory.com/174)