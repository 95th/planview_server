drop table if exists users;

create table users (
    id int auto_increment primary key,
    first_name varchar(250) not null,
    last_name varchar(250) not null,
    date_of_birth date not null,
    email varchar(250) not null,
    addr_line_1 varchar(250),
    addr_line_2 varchar(250),
    city varchar(250) not null,
    state varchar(250) not null,
    country varchar(250) not null,
    zip varchar(250) not null,
    user_name varchar(250) unique not null,
    password varchar(250) not null,
    role varchar(250) default 'USER',
    locked boolean not null default false,
    failed_tries int not null default 0,
    check role in ('USER', 'ADMIN')
);

insert into users (first_name, last_name, date_of_birth, email, city, state, country, zip, user_name, password, role)
values (
    'the', 'admin', PARSEDATETIME('01-01-2001', 'dd-MM-yyyy'), 'admin@planview.com', 'admin city', 'admin state',
    'admin country', 'admin zip', 'admin', '$2y$12$PqHYbsHorZGuRQNbI4C7wuUl5LqoNUUTe5Q4TCap/P3Ue1zjRz2Qu', 'ADMIN'
);


drop table if exists messages;

create table messages (
    id int auto_increment primary key,
    sender varchar(250) not null,
    recipient varchar(250) not null,
    subject varchar(512) not null,
    body varchar(4000) not null,
    date date not null
);

drop table if exists request_logs;

create table request_logs (
    id int auto_increment primary key,
    user_id varchar(250),
    url varchar(1024) not null,
    query_url varchar(1024),
    timestamp timestamp
);
