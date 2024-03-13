--liquibase formatted sql
--changeset luxury:1.0.7

create table if not exists brand
(
    id bigserial not null
        constraint pk_brand
            primary key,
    created timestamp,
    updated timestamp,
    title text,
    description text,
    image_id bigint,
    active boolean default true,
    number bigint,
    category_id bigint
);

create table if not exists collection
(
    id bigserial not null
        constraint collection_pkey
            primary key,
    created timestamp(6) with time zone,
    updated timestamp(6) with time zone,
    active boolean,
    number integer,
    title varchar(255)
);

create table if not exists brand_collections
(
    brand_id bigint not null,
    collections_id bigint not null
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
       (now(), now(), 'BENTLEY HOME');



create table if not exists category
(
    id bigserial not null
        constraint category_pkey
            primary key,
    created timestamp(6) with time zone,
    updated timestamp(6) with time zone,
    active boolean,
    number integer,
    title varchar(255)
);

create table if not exists type
(
    id bigserial not null
        constraint type_pkey
            primary key,
    created timestamp(6) with time zone,
    updated timestamp(6) with time zone,
    active boolean,
    description varchar(255),
    number integer,
    title varchar(255)
);

create table if not exists category_types
(
    category_id bigint not null
            references category,
    types_id bigint not null
            references type
);

create table if not exists brand_category
(
    category_id bigint not null
        references category,
    brands_id bigint not null
        references brand
);