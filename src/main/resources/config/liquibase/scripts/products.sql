--liquibase formatted sql
--changeset luxury:1.0.6

create table ticket.table_name
(
    id          BIGSERIAL,
    created     TIMESTAMP WITHOUT TIME ZONE,
    updated     TIMESTAMP WITHOUT TIME ZONE,
    price       int not null,
    in_stock    bool,
    description text,
    image_id    int8,
    materials   text,
    properties  jsonb,
    colors      text[]
);
