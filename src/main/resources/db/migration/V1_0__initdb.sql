--create beer table

create table favorites_beer
(
    id          uuid not null,
    beer_api_id int8 not null,
    rate        int4,
    user_id     uuid not null,
    name        varchar(64) unique,
    abv         float8,
    ibu         float8,
    ebc         float8,
    primary key (beer_api_id, id),
    constraint constraint_beer unique (user_id, beer_api_id)
);
--create food table
create table favorites_food
(
    id          uuid not null,
    user_id     uuid not null,
    beer_api_id int8 not null,
    rate        int4,
    text        varchar(512),
    primary key (id),
    constraint constraint_name unique (user_id, beer_api_id, text)
);