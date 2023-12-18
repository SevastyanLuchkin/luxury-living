--liquibase formatted sql
--changeset luxury:1.0.1

CREATE TABLE brand
(
    id          BIGSERIAL,
    created     TIMESTAMP WITHOUT TIME ZONE,
    updated     TIMESTAMP WITHOUT TIME ZONE,
    title       TEXT,
    description TEXT,
    image_id    int8,
    active      BOOLEAN DEFAULT TRUE,
    CONSTRAINT pk_brand PRIMARY KEY (id)
);

INSERT INTO Brand (created, updated, title)
VALUES (now(), now(), 'DOLCE & GABBANA CASA'),
       (now(), now(), 'BACCARAT'),
       (now(), now(), 'HERMES'),
       (now(), now(), 'LALIQUE'),
       (now(), now(), 'CHRISTOFLE'),
       (now(), now(), 'BERNARDAUD'),
       (now(), now(), 'ST. LOUIS'),
       (now(), now(), 'DAUM'),
       (now(), now(), 'HAVILAND'),
       (now(), now(), 'TSAR'),
       (now(), now(), 'GIEN'),
       (now(), now(), 'RIVIERE'),
       (now(), now(), 'BENTLEY HOME'),
       (now(), now(), 'LLANDRO');

--rollback drop table brand
