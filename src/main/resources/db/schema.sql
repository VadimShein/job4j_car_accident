CREATE TABLE if not exists types (
    id serial primary key,
    name varchar(255)
);

CREATE TABLE if not exists accident (
    id serial primary key,
    carNumber varchar(255),
    address varchar(255),
    description varchar(255),
    created timestamp,
    author varchar(255),
    status varchar(255),
    type_id int references types(id)
);

CREATE TABLE if not exists rules (
    id serial primary key,
    name varchar(255)
);

CREATE TABLE if not exists accident_rules (
    accident_id int references accident(id),
    rules_id int references rules(id)
);

CREATE TABLE if not exists authorities (
    id serial primary key,
    name varchar(255)
);

CREATE TABLE if not exists users (
    id serial primary key,
    username varchar(50),
    password varchar(100),
    enabled boolean,
    authority_id int references authorities(id)
);


