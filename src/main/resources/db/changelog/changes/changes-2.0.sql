--liquibase formatted sql

--changeset azicus:4.0
--preconditions onFail:MARK_RAN onError:MARK_RAN
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM roles
insert into roles values (1, 'ROLE_STUDENT'),
                         (2, 'ROLE_TEACHER'),
                         (3, 'ROLE_ADMIN');