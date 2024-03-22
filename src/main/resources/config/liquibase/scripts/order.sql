create table "order"
(
    id         bigserial
        constraint order_pk
            primary key,
    created    timestamp(6) with time zone,
    updated    timestamp(6) with time zone,
    product_id bigint,
    phone      text,
    email      text
);