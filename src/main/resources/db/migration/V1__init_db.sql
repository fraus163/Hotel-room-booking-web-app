create table rooms (
    id bigint primary key generated always as identity,
    name varchar(50) not null,
    description varchar,
    people int,
    area int,
    price_per_night decimal(10, 2) not null,
    enabled boolean not null
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
    room_id bigint not null,
    tag_id bigint not null,

    constraint fk_tr_room
        foreign key (room_id)
        references rooms(id),
    constraint fk_tr_tag
        foreign key (tag_id)
        references tags(id),

    primary key (room_id, tag_id)
);

create table reservations (
    id bigint primary key generated always as identity,
    user_id bigint not null,
    room_id bigint not null,
    days int not null,
    created_at timestamptz not null,
    full_price decimal(10, 2) not null,
    arrival_date date not null,
    status varchar(25) not null,

    constraint fk_reservation_room
        foreign key (room_id)
        references rooms(id),
    constraint fk_reservation_user
        foreign key (user_id)
        references users(id)
);