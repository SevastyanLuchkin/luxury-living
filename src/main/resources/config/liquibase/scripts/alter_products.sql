--liquibase formatted sql
--changeset luxury:1.0.8

ALTER TABLE orders
    ADD handled bool default false;

--rollback drop table brand
