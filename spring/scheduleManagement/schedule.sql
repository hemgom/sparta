--Database (=schema) 생성 쿼리
CREATE DATABASE schedule_management;

--Table 생성 쿼리
CREATE TABLE 'schedule' (
    id int NOT NULL AUTO_INCREMENT,
    body varchar(150) NOT NULL,
    author varchar(20) NOT NULL,
    password varchar(6) NOT NULL,
    create_at DATETIME NOT NULL,
    update_at DATETIME NOT NULL,
    PRIMARY KEY (id)
);