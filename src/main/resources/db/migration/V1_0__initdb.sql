CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

SELECT uuid_generate_v1();

create table favorites
(
    id      uuid default uuid_generate_v1(),
    beer_id int8 not null,
    rate    int4,
    user_id uuid not null,
    primary key (beer_id, id)
);

create table favorites_food
(
    id   uuid default uuid_generate_v1(),
    rate int4,
    text varchar(512),
    primary key (id)
)