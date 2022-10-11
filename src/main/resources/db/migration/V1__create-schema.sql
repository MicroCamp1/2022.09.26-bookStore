create table author
(
    id         integer      not null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    book_id    integer,
    primary key (id)
);
create table hibernate_sequence
(
    next_val bigint
);
insert into hibernate_sequence
values (1);
create table books (id integer not null, isbn varchar(255) not null, price double precision not null, title varchar(255) not null, primary key (id));
alter table books add constraint UK_books_isbn unique (isbn);
alter table author add constraint FK_author_book_id foreign key (book_id) references books (id);