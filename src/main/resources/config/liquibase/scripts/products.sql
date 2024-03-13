--liquibase formatted sql
--changeset luxury:1.0.6

create table product
(
    id            bigserial not null
        constraint product_pkey
            primary key,
    created       timestamp(6) with time zone,
    updated       timestamp(6) with time zone,
    active        boolean,
    article       varchar(255),
    colors        text[],
    country       varchar(255),
    description   varchar(255),
    image_id      bigint,
    image_ids     bigint[],
    in_stock      boolean default true,
    materials     varchar(255),
    price         integer,
    properties    jsonb,
    title         varchar(255),
    volume        varchar(255),
    brand_id      bigint
        constraint fks6cydsualtsrprvlf2bb3lcam
            references brand,
    category_id   bigint
        constraint fk1mtsbur82frn64de7balymq9s
            references category,
    collection_id bigint
        constraint fk1m7avyryg7yow6ytttlt7qcun
            references collection,
    type_id       bigint
        constraint fkq3fvcsydiaotwy3iqn1erqsfd
            references type
);
