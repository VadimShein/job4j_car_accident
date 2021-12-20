CREATE TABLE accident_type (
    id serial primary key,
    name varchar(255)
);

CREATE TABLE accident (
    id serial primary key,
    carNumber varchar(255),
    address varchar(255),
    description varchar(255),
    created timestamp,
    author varchar(255),
    status varchar(255),
    type_id int references accident_type(id)
);

CREATE TABLE rules (
    id serial primary key,
    name varchar(255)
);

CREATE TABLE accident_rules (
    accident_id int references accident(id),
    rules_id int references rules(id)
);