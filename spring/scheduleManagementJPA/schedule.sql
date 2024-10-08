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