CREATE TABLE users (
    username VARCHAR(50) primary key NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled boolean default true
);

CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL references users(username),
    authority VARCHAR(50) NOT NULL
);