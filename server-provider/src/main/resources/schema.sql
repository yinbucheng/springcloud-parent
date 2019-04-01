create database if not exists server_test;

create table if not exists server_test.t_user (
id bigint primary key auto_increment,
name varchar(20),
age int,
gender varchar(20)
)engine=innodb;