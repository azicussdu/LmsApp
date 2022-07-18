--liquibase formatted sql

--changeset azicus:1.0
create table if not exists users (id bigint primary key, name varchar(255) not null, username varchar(255) not null, password varchar(255) not null);
--rollback drop table users;

--changeset azicus:2.0
create table if not exists roles (id bigint primary key, name varchar(255) not null);
--rollback drop table roles;

--changeset azicus:3.0
create table if not exists users_roles (user_id bigint not null, roles_id bigint not null);
--rollback drop table users_roles;