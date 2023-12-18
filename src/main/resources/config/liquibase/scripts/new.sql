--liquibase formatted sql
--changeset luxury:1.0.4

CREATE TABLE news
(
    id          BIGSERIAL,
    created     TIMESTAMP WITHOUT TIME ZONE,
    updated     TIMESTAMP WITHOUT TIME ZONE,
    title       TEXT,
    description TEXT,
    image_id    INT8,
    news_date   TIMESTAMP WITHOUT TIME ZONE,
    lang        TEXT default 'RUS',
    CONSTRAINT pk_news PRIMARY KEY (id)
);

INSERT INTO news (created, updated, title, description, news_date)
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


