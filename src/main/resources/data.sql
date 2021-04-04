drop table if exists user;

create table user (
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

insert into user (first_name, last_name, date_of_birth, email, city, state, country, zip, user_name, password, role)
values (
    'the', 'admin', PARSEDATETIME('01-01-2001', 'dd-MM-yyyy'), 'admin@planview.com', 'admin city', 'admin state',
    'admin country', 'admin zip', 'admin', '$2y$12$PqHYbsHorZGuRQNbI4C7wuUl5LqoNUUTe5Q4TCap/P3Ue1zjRz2Qu', 'ADMIN'
);


drop table if exists message;

create table message (
    id int auto_increment primary key,
    sender varchar(250) not null,
    recipient varchar(250) not null,
    subject varchar(512) not null,
    body varchar(4000) not null,
    date date not null
);


drop table if exists api_log;

create table api_log (
    id int auto_increment primary key,
    user_id varchar(250),
    url varchar(1024) not null,
    query_url varchar(1024),
    timestamp timestamp
);

drop table if exists timesheet;
drop table if exists work_assignment;
drop table if exists work_item;
drop table if exists work_type;

create table work_type (
    id int auto_increment primary key,
    name varchar(250) unique not null
);

create table work_item (
    id int auto_increment primary key,
    name varchar(250) unique not null,
    type_id int not null,
    foreign key (type_id) references work_type(id)
);

create table work_assignment (
    id int auto_increment primary key,
    user_id int not null,
    item_id int not null,
    foreign key (user_id) references user(id),
    foreign key (item_id) references work_item(id),
    unique(user_id, item_id)
);

create table timesheet (
    id int auto_increment primary key,
    assignment_id int not null,
    date date not null,
    hours int,
    foreign key (assignment_id) references work_assignment(id),
    unique(assignment_id, date)
);
