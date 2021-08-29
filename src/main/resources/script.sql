create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres;

create table if not exists author
(
    id            bigserial not null
        constraint author_pkey
            primary key,
    country       varchar(255),
    date_of_birth date,
    fio           varchar(255)
);

alter table author
    owner to postgres;

create table if not exists book
(
    id    bigserial not null
        constraint book_pkey
            primary key,
    date  date,
    isbn  varchar(255),
    title varchar(255)
);

alter table book
    owner to postgres;

create table if not exists book_authors
(
    books_id   bigint not null
        constraint fkmuhqocx8etx13u6jrtutnumek
            references book,
    authors_id bigint not null
        constraint fk551i3sllw1wj7ex6nir16blsm
            references author
);

alter table book_authors
    owner to postgres;


