CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";

SELECT uuid_generate_v1();

insert into favorites_beer(id, user_id, beer_api_id, rate, name, abv, ibu, ebc)
values (uuid_generate_v1(), 'e0087092-22b2-11ed-a2fe-b7147dec817d', 7, 5, 'Jasne', 3.0, 2.5, 4.3),
       (uuid_generate_v1(), '070816e8-0e76-432d-995f-700534b28f24', 19, 4, 'Gref', 5.6, 3.1, 9.9),
       (uuid_generate_v1(), 'd4ce25e2-22ac-11ed-b5a4-77ac144b4ca4', 14, 2, 'Pilsner', 8.8, 1.0, 4.3),
       (uuid_generate_v1(), 'd4ce25e2-22ac-11ed-b5a4-77ac144b4ca4', 12, 3, 'Kozel', 12.3, 7.0, 4.3),
       (uuid_generate_v1(), 'd4ce25e2-22ac-11ed-b5a4-77ac144b4ca4', 16, 2, 'Heiniken', 14.0, 0.8, 7.0),
       (uuid_generate_v1(), 'd4cbb51e-22ac-11ed-b5a2-fb8148c2bec2', 4, 3, 'Aliva', 5.9, 8.7, 0.0),
       (uuid_generate_v1(), 'd4cbb51e-22ac-11ed-b5a2-fb8148c2bec2', 5, 1, 'Zero', 4.0, 12.0, 31.2),
       (uuid_generate_v1(), 'd4cbb51e-22ac-11ed-b5a2-fb8148c2bec2', 8, 4, 'Chen', 0.9, 0.0, 11.0),
       (uuid_generate_v1(), 'd4cbb51e-22ac-11ed-b5a2-fb8148c2bec2', 36, 5, 'IPAGold', 1.1, 2.0, 7.0),
       (uuid_generate_v1(), 'e008978e-22b2-11ed-a2ff-cbfae0543424', 36, 5, 'Brno', 2.8, 2.9, 4.0),
       (uuid_generate_v1(), '070816e8-0e76-432d-995f-700534b28f24', 31, 2, 'Zywec', 7.0, 3.0, 24.0),
       (uuid_generate_v1(), 'e008978e-22b2-11ed-a2ff-cbfae0543424', 44, 4, 'Germany Beer', 0.8, 8.0, 0.5),
       (uuid_generate_v1(), '070816e8-0e76-432d-995f-700534b28f24', 17, 5, 'Narodowe', 7.0, 14.0, 4.0);

insert into favorites_food(id, user_id, beer_api_id, rate, text)
values (uuid_generate_v1(), 'd4cbb51e-22ac-11ed-b5a2-fb8148c2bec2', 4, 5, 'Meat'),
       (uuid_generate_v1(), '070816e8-0e76-432d-995f-700534b28f24', 17, 4, 'Cheese with'),
       (uuid_generate_v1(), 'd4cbb51e-22ac-11ed-b5a2-fb8148c2bec2', 4, 4,
        'Pizza with pork and cheese and peppers, spacy'),
       (uuid_generate_v1(), 'e008978e-22b2-11ed-a2ff-cbfae0543424', 36, 1, 'Spicy crab cakes'),
       (uuid_generate_v1(), '070816e8-0e76-432d-995f-700534b28f24', 17, 3, 'Pan seared steak'),
       (uuid_generate_v1(), 'd4ce25e2-22ac-11ed-b5a4-77ac144b4ca4', 12, 2, 'Chicken fried steak with cheesy mash'),
       (uuid_generate_v1(), 'e008978e-22b2-11ed-a2ff-cbfae0543424', 36, 5,
        'Roast chicken with vegetables and wild rice'),
       (uuid_generate_v1(), 'd4ce25e2-22ac-11ed-b5a4-77ac144b4ca4', 12, 3,
        'Candied almond and blue cheese rocket salad'),
       (uuid_generate_v1(), '070816e8-0e76-432d-995f-700534b28f24', 17, 1, 'White Chocolate and Pistachio Cheesecake'),
       (uuid_generate_v1(), 'e0087092-22b2-11ed-a2fe-b7147dec817d', 7, 3, 'Pork chops with spicy orange marmalade'),
       (uuid_generate_v1(), '070816e8-0e76-432d-995f-700534b28f24', 19, 4, 'Roast beef with a herb and malt crust'),
       (uuid_generate_v1(), '070816e8-0e76-432d-995f-700534b28f24', 19, 2, 'Grilled salmon tacos'),
       (uuid_generate_v1(), '070816e8-0e76-432d-995f-700534b28f24', 19, 5, 'Ham and mustard crisps'),
       (uuid_generate_v1(), 'e0087092-22b2-11ed-a2fe-b7147dec817d', 7, 3, 'Walnut and goas cheese salad with dressing');
