--liquibase formatted sql
--changeset luxury:1.0.2

CREATE TABLE email_subscription
(
    id      BIGSERIAL,
    created TIMESTAMP WITHOUT TIME ZONE,
    updated TIMESTAMP WITHOUT TIME ZONE,
    email   TEXT,
    active  BOOLEAN,
    CONSTRAINT pk_emailsubscription PRIMARY KEY (id)
);

--rollback drop table email_subscription