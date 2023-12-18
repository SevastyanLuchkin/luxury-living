--liquibase formatted sql
--changeset luxury:1.0.5

CREATE TABLE image
(
    id      BIGSERIAL,
    created TIMESTAMP WITHOUT TIME ZONE,
    updated TIMESTAMP WITHOUT TIME ZONE,
    title   TEXT,
    picture bytea,
    CONSTRAINT pk_images PRIMARY KEY (id)
);
