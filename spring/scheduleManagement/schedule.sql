-- 1.필수 기능
-- Database (=schema) 생성 쿼리
CREATE DATABASE schedule_management;

-- Table 생성 쿼리
CREATE TABLE schedule (
    id int NOT NULL AUTO_INCREMENT,
    body varchar(150) NOT NULL,
    author varchar(20) NOT NULL,
    password varchar(6) NOT NULL,
    create_at DATETIME NOT NULL,
    update_at DATETIME NOT NULL,
    PRIMARY KEY (id)
);


-- 2.도전 기능
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