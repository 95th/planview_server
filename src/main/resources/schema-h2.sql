drop table if exists timesheet;
drop table if exists work_assignment;
drop table if exists work_item;
drop table if exists work_type;
drop table if exists api_log;
drop table if exists message;
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

create table message (
    id int auto_increment primary key,
    sender int not null,
    recipient int not null,
    subject varchar(512) not null,
    body varchar(4000) not null,
    date date not null,
    foreign key (sender) references user(id),
    foreign key (recipient) references user(id)
);

create table api_log (
    id int auto_increment primary key,
    user_id int not null,
    url varchar(1024) not null,
    query_params varchar(1024),
    timestamp timestamp
);

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
    week_start_date date not null,
    hours_monday int,
    hours_tuesday int,
    hours_wednesday int,
    hours_thursday int,
    hours_friday int,
    last_updated timestamp not null,
    foreign key (assignment_id) references work_assignment(id),
    unique(assignment_id, week_start_date)
);
