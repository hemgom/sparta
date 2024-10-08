# Index
1. [프로젝트 설명 및 목표](#schedule-management)
2. [요구사항 정리](#요구사항)
    - [필수 기능](#필수-기능)
    - [도전 기능](#도전-기능)
3. [API 명세서](#api-명세서)
    - [필수 기능](#필수-기능-1)
    - [도전 기능](#도전-기능-1)
4. [ERD](#erd)
    - [필수 기능](#필수-기능-2)
    - [도전 기능](#도전-기능-2)
5. [SQL - DB 및 Table 생성](#sql)
6. [작성 포스팅](#구현-및-트러블-슈팅-관련-포스팅)

# Schedule Management
부트캠프 `CH 3` 에서 학습한 `Spring` 을 토대로 구현한 `일정 관리 앱` 프로젝트<br/>
`Spring + JDBC + MySQL` 을 통해 기본적인 `CRUD` 를 숙달하는 것이 목표!
<br/><br/><br/>

# 요구사항
필수에 해당하는 `Lv.3` 까지의 요구사항을 한 번에 반영한 뒤 이후 `Lv4, Lv5` 요구사항을 반영해 볼 생각이다.
## 필수 기능
### Lv.0
- [x] : 사용자 요청에 대한 응답으로 데이터 전송시 `비밀번호` 에 대한 정보는 항상 제외되어야 한다.
- [x] : `3 Layer Architecture` 에 따라 각 `Layer` 목적에 맞게 구현해야 한다.
- [x] : `CRUD` 필수 기능들은 모두 `DB 연결` 및 `JDBC` 를 사용해 구현해야 한다.
### Lv.1
- [x] : API 명세서 작성
- [x] : ERD 작성
- [x] : `테이블` 생성에 사용된 `Query` 를 담은 `schedule.sql` 작성
    - 해당 문서는 애플리케이션 구현보다 이후 과제 확인에서 필요하기 때문에 작성이 필요함!
### Lv.2
- [x] : 스케줄(일정) 생성 - Create
    - 생성에 필요한 데이터 : `할 일`, `작성자명`, `비밀번호`, `작성일/수정일`
        - `작성일/수정일` 같은 경우 `날짜 및 시간` 을 모두 포함한 데이터여야 함
        - 또한 생성시 두 데이터는 같은 값을 가짐
    - 스케줄마다 사용되는 `id(primary key)` 값이 생성할 때마다 `자동` 으로 생성되어야 함
- [x] : 전체 스케줄(일정) 조회 - Read
    - `수정일(YYYY-MM-DD)`, `작성자명` 에 해당하는 모든 스케줄 목록 조회
        - 두 조건을 모두 만족하거나, 둘 중 하나만을 만족하거나, 두 조건 모두 만족하지 않는 경우도 조회할 수 있어야함
        - `수정일` 을 기준으로 `내림차순` 정렬해 조회할 것
- [x] : 선택 스케줄(일정) 조회 - Read
    - 선택한 특정 스케줄 정보를 조회할 수 있어야 함
    - 스케줄 정보의 `id` 를 통해 조회한다.
### Lv.3
- [x] : 선택한 일정 수정 - Update
    - 수정 가능한 데이터는 `할 일`, `작성자명` 뿐이다.
    - 서버에 수정을 요청할 때는 `비밀번호` 를 함께 전달해야 한다.
        - 물론 `비밀번호` 정보가 일치하지 않는다면 수정은 수행되지 않고 오류 코드 및 메시지가 반환되어야 한다.
    - 수정이 이루어지면 `수정일` 데이터가 수정 시점의 `날짜/시간` 데이터로 변경되어야 한다.
- [x] : 선택한 일정 삭제 - Delete
    - 서버에 삭제를 요청할 때는 `비밀번호` 를 함께 전달해야 한다.
        - 물론 `비밀번호` 정보가 일치하지 않는다면 삭제는 수행되지 않고 오류 코드 및 메시지가 반환되어야 한다.
<br/><br/>
## 도전 기능
### Lv.4
- [x] : 연관관계 설정
    - `작성자명` 데이터는 `동명인` 의 존재 때문에 명확하게 구별이 어려울 수 있다.
    - `작성자` 에 대한 정보를 가진 별도의 테이블을 생성 후, 신규 테이블의 `PK(primary key, 기본 키, 고유 키)` 를 기존 테이블의 `FK(foreign key, 외래 키, 참조 키)` 로 사용해 두 테이블은 연관 짓도록 한다.
        - `작성자` 는 `이름`, `이메일`, `등록일`, `수정일` 정보를 가지고 있다.
        - 적절한 데이터라면 임의로 `작성자` 에 `데이터(속성, 필드)` 를 추가해도 된다.
### Lv.5
- [x] : `DB` 에서 `스케줄, 작성자` 목록을 `전체` 가 아닌 `페이지` 로 나누어 조회할 수 있도록 한다.
    - 간단하게 이야기하면 웹 상에서 `페이지 번호` 를 누를 때 선택한 페이지에 해당하는 정보들만 노출되는 것을 떠올리면 될 것 같다.
    - `페이지 번호` 와 `페이지 크기` 를 기준으로 전체 데이터 목록을 조회할 수 있어야 한다.
    - 조회한 스케줄 목록에는 `작성자 이름` 이 포함되어야 한다.
    - 조회 시 지정한 `페이지 번호` 보다 적은 목록을 조회할 경우 `빈 배열` 을 반환한다.
    - `페이징` 을 관리하는 `Paging` 객체를 별도로 구현해 활용해 보자.
      <br/><br/><br/>

# API 명세서
## 필수 기능
|    기능    | Http method |          URL           |   Request parameter   |     Response      | HttpStatus |
|:--------:|:-----------:|:----------------------:|:---------------------:|:-----------------:|:----------:|
|  일정 생성   |    POST     |       /schedule        |    할일, 작성자명, 비밀번호     | id, 할일, 작성자명, 작성일 | 201: 정상등록  |
|  일정 조회   |     GET     | /schedule/{scheduleId} |           -           |     단건 응답 정보      | 200: 정상조회  |
| 일정 목록 조회 |     GET     |       /schedule        |       수정일, 작성자명       |     다건 응답 정보      | 200: 정상조회  |
|  일정 수정   |     PUT     | /schedule/{scheduleId} | 비밀번호, 수정 내용(할일, 작성자명) |   id, 할일, 작성자명    | 202: 정상수정  |
|  일정 삭제   |   DELETE    | /schedule/{scheduleId} |         비밀번호          |         -         | 202: 정상삭제  |

<br/>

## 도전 기능
|    기능    | Http method |            URL             |        Request parameter         |           Response           |  HttpStatus   |
|:--------:|:-----------:|:--------------------------:|:--------------------------------:|:----------------------------:|:-------------:|
|  일정 생성   |    POST     |         /schedule          |    할 일, 비밀전호, 작성자 이름, 작성자 이메일    | 일정 ID, 할 일, 작성자 이름, 작성일, 수정일 |  201 Created  |
|  일정 조회   |     GET     |   /schedule/{scheduleId}   |              일정 ID               | 일정 ID, 할 일, 작성자 이름, 작성일, 수정일 |    200 OK     |
| 일정 목록 조회 |     GET     | /schedule/search-condition | 검색 조건(수정일, 작성자명, 페이지 번호, 페이지 크기) |   페이지 번호, 일정 목록(일정 정보 포함)    |    200: OK    |
|  일정 수정   |     PUT     |   /schedule/{scheduleId}   |  일정 ID, 비밀번호, 수정 정보(할 일, 작성자명)   | 일정 ID, 할 일, 작성자 이름, 작성일, 수정일 | 202: Accepted |
|  일정 삭제   |   DELETE    |   /schedule/{scheduleId}   |               비밀번호               |              -               | 202 Accepted  |


<br/><br/>

# ERD
## 필수 기능
![ERD - essential features](image/ERD%20-%20essential%20features.png)
<br/><br/>

## 도전 기능
![ERD - challenge features](image/ERD%20-%20challenge%20feature.png)
<br/><br/><br/>

# SQL
DB 스키마와 해당 스키마의 테이블을 생성하는 `Query` 문들을 나열한 것, 요구사항 대로 `schedule.sql` 도 작성해 두었으며 `MySQL Workbench` 를 통해서도 생성할 수 있었으나 평소 잘 사용해보지 않았던 `MySQL Command line Client` 로 생성해 보았다.
## 필수 기능
```mysql
# Database (=schema) 생성 쿼리
CREATE DATABASE schedule_management;

# Table 생성 쿼리
CREATE TABLE 'schedule' (
    id int NOT NULL AUTO_INCREMENT,
    body varchar(150) NOT NULL,
    author varchar(20) NOT NULL,
    password varchar(6) NOT NULL,
    create_at DATETIME NOT NULL,
    update_at DATETIME NOT NULL,
    PRIMARY KEY (id)
);
```
<br/>

## 도전 기능
```mysql
-- 기존 schedule_management.schedule 삭제
DROP TABLE schedule;

-- author Table 생성 쿼리
CREATE TABLE author (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(20) NOT NULL,
    e_mail varchar(254) NOT NULL,
    create_at DATETIME NOT NULL,
    update_at DATETIME NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX e_mail_unique (e_mail ASC) VISIBLE
);

-- schedule Table 생성 쿼리
CREATE TABLE schedule (
    id int NOT NULL AUTO_INCREMENT,
    body varchar(150) NOT NULL,
    author_id int NOT NULL,
    password varchar(6) NOT NULL,
    create_at DATETIME NOT NULL,
    update_at DATETIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES author (id)
);
```
<br/><br/><br/>

# 구현 및 트러블 슈팅 관련 포스팅
해당 프로젝트 진행간 구현한 내용 또는 마주한 문제를 해결한 내용을 다룬 포스팅 목록을 링크 해두었다.
- [DB 세팅 및 IntelliJ 연결](https://development-diary-for-me.tistory.com/159)
- [DB 연결 + 일정 생성 API 테스트](https://development-diary-for-me.tistory.com/160)
- [Spring Framework 6.x 이상 파라미터 인식 오류](https://development-diary-for-me.tistory.com/162)
- [API 테스트 - 일정 수정 및 삭제](https://development-diary-for-me.tistory.com/163)
- [예외 처리 적용 및 API 테스트](https://development-diary-for-me.tistory.com/165)
- [Level 4 요구사항 정리, DB 세팅](https://development-diary-for-me.tistory.com/166)
- [Level 4~5 요구사항 반영 후 API 테스트](https://development-diary-for-me.tistory.com/167)