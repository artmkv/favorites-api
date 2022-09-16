CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

SELECT uuid_generate_v1();

create table favorites_beer
(
    id          uuid default uuid_generate_v1(),
    beer_api_id int8 not null,
    rate        int4,
    user_id     uuid not null,
    name        varchar(64),
    abv         float8,
    ibu         float8,
    ebc         float8,
    primary key (beer_api_id, id)
);

create table favorites_food
(
    id          uuid default uuid_generate_v1(),
    user_id     uuid not null,
    beer_api_id int8 not null,
    rate        int4,
    text        varchar(512),
    primary key (id)
);