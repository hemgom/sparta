-- Database(schema) 생성
CREATE DATABASE schedule_management_jpa;


-- Lv.1 요구사항 : 'schedule' 테이블 생성
CREATE TABLE schedule (
    id bigint not null auto_increment,
    author varChar(20) not null,
    title varChar(100) not null,
    body varChar(250) not null,
    create_at timestamp not null,
    update_at timestamp not null,
    primary key (id)
);


-- Lv.2 요구사항 : 'comment' 테이블 생성
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


-- Lv.4 요구사항
-- Database(= schedule_management_jpa) Table 삭제
use schedule_management_jpa
drop table comment;
drop table schedule;

-- 'member' 테이블 생성
CREATE TABLE member (
    id bigint not null auto_increment,
    name varChar(20) not null,
    email varCHar(350) not null,
    create_at timestamp not null,
    update_at timestamp not null,
    primary key (id)
);

-- 'schedule' 테이블 생성
CREATE TABLE schedule (
    id bigint not null auto_increment,
    title varChar(100) not null,
    body varChar(250) not null,
    create_at timestamp not null,
    update_at timestamp not null,
    member_id bigint not null,
    primary key (id),
    foreign key (member_id) references member (id)
);

-- 'comment' 테이블 생성
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

-- 'schedule_manager' 테이블 생성
CREATE TABLE schedule_manager (
    id bigint not null auto_increment,
    schedule_id bigint not null,
    member_id bigint not null,
    primary key (id),
    foreign key (schedule_id) references schedule (id),
    foreign key (member_id) references member (id)
);