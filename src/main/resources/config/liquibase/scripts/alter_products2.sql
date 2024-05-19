--liquibase formatted sql
--changeset luxury:1.0.9

ALTER TABLE product
    ADD liked bool default false;


