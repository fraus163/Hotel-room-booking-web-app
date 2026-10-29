create table rooms (
    id bigint primary key generated always as identity,
    name varchar(50) not null,
    description varchar,
    people int,
    area int,
    price_per_night decimal not null
);

create table users (
    id bigint primary key generated always as identity,
    username varchar(25) not null unique,
    email varchar(50) not null unique,
    password varchar(120) not null
);