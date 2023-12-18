--liquibase formatted sql
--changeset luxury:1.0.2

CREATE TABLE faq_item
(
    id              BIGSERIAL,
    created         TIMESTAMP WITHOUT TIME ZONE,
    updated         TIMESTAMP WITHOUT TIME ZONE,
    title_ru        TEXT,
    title_eng       TEXT,
    description_ru  TEXT,
    description_eng TEXT,
    lang            TEXT    default 'RUS',
    active          BOOLEAN default true,
    CONSTRAINT pk_faqitem PRIMARY KEY (id)
);

INSERT INTO faq_item (created, updated, title_ru, description_ru)
values (now(), now(), 'Возможна ли доставка в другие регионы?',
        'Да, у нас есть бесплатная доставка в любой регион и город России'),
       (now(), now(), 'Какие доступны формы оплаты?',
        'Вы можете оплатить товары через наличный расчет, банковскими картами,  по реквизитам компании или QR-коду'),
       (now(), now(), 'Что делать, если у меня нет времени (возможности) приехать  в салон?',
        'Наши сотрудники приедут к Вам в любое удобное время с предварительно согласованным ассортиментом. При необходимости, оплата будет принята  на месте со всеми кассовыми документами (чеками).'),
       (now(), now(), 'Работаете ли вы с юридическими лицами?',
        'Да, работаем и предоставляем все необходимые в таких случаях документы'),
       (now(), now(), 'Если мой дом находится за пределами Краснодара,  возможен ли выезд декораторов (дизайнеров)?',
        'Да, конечно, наши специалисты приедут в любое удобное для Вас время.  Заявку можно оставить по телефону +7 (861) 222-22-23 или написать на электронную почту: LLsalon@mail.ru');

--rollback drop table faq

