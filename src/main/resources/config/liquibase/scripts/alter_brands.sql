--liquibase formatted sql
--changeset luxury:1.0.10

alter table brand
    add column country text,
    add column logo_id int8;
