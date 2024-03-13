--liquibase formatted sql
--changeset luxury:1.0.4

create table news
(
    id              bigserial not null
        constraint pk_news
            primary key,
    created         timestamp,
    updated         timestamp,
    title           text,
    description_eng text,
    description_rus text,
    image_id        bigint,
    news_date       timestamp,
    lang            text default 'RUS'::text,
    active          boolean,
    title_eng       varchar(255),
    title_ru        varchar(255)
);


INSERT INTO news (created, updated, title, description_rus, news_date)
values (now(), now(), 'Разнообразие коллекций HERMES в нашем салоне',
        'Разнообразие коллекций HERMES в нашем салонеРазнообразие коллекций HERMES в нашем салонеРазнообразие коллекций HERMES в нашем салонеРазнообразие коллекций HERMES в нашем салонеРазнообразие коллекций HERMES в нашем салоне',
        '2022-12-17'),
       (now(), now(), 'Новогодние скидки на Fendi Casa и Ritz Paris',
        'Разнообразие коллекций HERMES в нашем салонеРазнообразие коллекций HERMES в нашем салонеРазнообразие коллекций HERMES в нашем салонеРазнообразие коллекций HERMES в нашем салонеРазнообразие коллекций HERMES в нашем салоне',
        '2022-11-07'),
       (now(), now(), 'Christmas Selection with Baccarat',
        'Разнообразие коллекций HERMES в нашем салонеРазнообразие коллекций HERMES в нашем салонеРазнообразие коллекций HERMES в нашем салонеРазнообразие коллекций HERMES в нашем салонеРазнообразие коллекций HERMES в нашем салоне',
        '2022-10-03'),
       (now(), now(), 'Luxury Living бьет все рекорды', 'Лухури ливинг признан лидером в мире роскоши', '2022-09-02'),
       (now(), now(), 'Christmas Selection with Baccarat',
        'Разнообразие коллекций HERMES в нашем салонеРазнообразие коллекций HERMES в нашем салонеРазнообразие коллекций HERMES в нашем салонеРазнообразие коллекций HERMES в нашем салонеРазнообразие коллекций HERMES в нашем салоне',
        '2022-08-01');

--rollback drop table faq


