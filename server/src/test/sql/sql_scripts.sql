drop table users;

create table users (
    id       integer primary key autoincrement,
    login    text    not null   unique,
    password text    not null,
    nickname text    not null   unique
);

insert into users(login, password, nickname)
values ('Vasya','gsogso','Basil');

insert into users(login, password, nickname)
values ('Petya','tsar','Petr-I');

commit;

select * from users;
