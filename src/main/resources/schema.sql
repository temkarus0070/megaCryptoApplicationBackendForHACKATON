create table IF NOT EXISTS users
(
    username varchar(25),
    password varchar(255)
);

create table IF NOT EXISTS currencies
(
    figi       varchar(128),
    last_price double
);

