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
    password varchar(120) not null,
    role varchar(25) not null,
    enabled boolean not null
);

create table tags (
    id bigint primary key generated always as identity,
    name varchar(25) not null unique
);

create table tags_rooms (
    room_id bigint,
    tag_id bigint,

    constraint fk_tr_room
        foreign key (room_id)
        references rooms(id),
    constraint fk_tr_tag
        foreign key (tag_id)
        references tags(id),

    primary key (room_id, tag_id)
);