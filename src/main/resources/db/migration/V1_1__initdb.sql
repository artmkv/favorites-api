CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

SELECT uuid_generate_v1();

create table favorites
(
    id          uuid not null default uuid_generate_v1(),
    beer_api_id int8 not null,
    rate        int4,
    user_id     int8 not null,
    primary key (beer_api_id, id)
);

create table favorites_food
(
    id   uuid not null default uuid_generate_v1(),
    rate int4,
    text varchar(255),
    primary key (id)
)