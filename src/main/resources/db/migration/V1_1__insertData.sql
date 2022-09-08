-- INSERT INTO favorites_beer(user_id, beer_api_id, rate)
-- VALUES ('e0087092-22b2-11ed-a2fe-b7147dec817d', 7, 5),
--        ('e0087092-22b2-11ed-a2fe-b7147dec817d', 6, 4),
--        ('e0087092-22b2-11ed-a2fe-b7147dec817d', 3, 4),
--        ('e0087092-22b2-11ed-a2fe-b7147dec817d', 8, 1),
--        ('e0087092-22b2-11ed-a2fe-b7147dec817d', 33, 3),
--        ('e0087092-22b2-11ed-a2fe-b7147dec817d', 45, 2),
--        ('e0087092-22b2-11ed-a2fe-b7147dec817d', 17, 5),
--        ('e0087092-22b2-11ed-a2fe-b7147dec817d', 14, 5),
--        ('d4ce25e2-22ac-11ed-b5a4-77ac144b4ca4', 3, 5),
--        ('d4ce25e2-22ac-11ed-b5a4-77ac144b4ca4', 14, 2),
--        ('d4ce25e2-22ac-11ed-b5a4-77ac144b4ca4', 12, 3),
--        ('d4ce25e2-22ac-11ed-b5a4-77ac144b4ca4', 16, 2),
--        ('d4cbb51e-22ac-11ed-b5a2-fb8148c2bec2', 4, 3),
--        ('d4cbb51e-22ac-11ed-b5a2-fb8148c2bec2', 5, 1),
--        ('d4cbb51e-22ac-11ed-b5a2-fb8148c2bec2', 8, 4),
--        ('d4cbb51e-22ac-11ed-b5a2-fb8148c2bec2', 36, 5),
--        ('e008978e-22b2-11ed-a2ff-cbfae0543424', 36, 5),
--        ('070816e8-0e76-432d-995f-700534b28f24', 31, 2),
--        ('e008978e-22b2-11ed-a2ff-cbfae0543424', 44, 4),
--        ('070816e8-0e76-432d-995f-700534b28f24', 17, 5);

INSERT INTO favorites_beer(user_id, beer_api_id, rate, name, abv, ibu, ebc)
VALUES ('e0087092-22b2-11ed-a2fe-b7147dec817d', 7, 5, 'Jasne', 3.0, 2.5, 4.3),
       ('070816e8-0e76-432d-995f-700534b28f24', 19, 4, 'Gref', 5.6, 3.1, 9.9),
       ('d4ce25e2-22ac-11ed-b5a4-77ac144b4ca4', 14, 2, 'empty', 0.0, 0.0, 0.0),
       ('d4ce25e2-22ac-11ed-b5a4-77ac144b4ca4', 12, 3, 'empty', 0.0, 0.0, 0.0),
       ('d4ce25e2-22ac-11ed-b5a4-77ac144b4ca4', 16, 2, 'empty', 0.0, 0.0, 0.0),
       ('d4cbb51e-22ac-11ed-b5a2-fb8148c2bec2', 4, 3, 'empty', 0.0, 0.0, 0.0),
       ('d4cbb51e-22ac-11ed-b5a2-fb8148c2bec2', 5, 1, 'empty', 0.0, 0.0, 0.0),
       ('d4cbb51e-22ac-11ed-b5a2-fb8148c2bec2', 8, 4, 'empty', 0.0, 0.0, 0.0),
       ('d4cbb51e-22ac-11ed-b5a2-fb8148c2bec2', 36, 5, 'empty', 0.0, 0.0, 0.0),
       ('e008978e-22b2-11ed-a2ff-cbfae0543424', 36, 5, 'empty', 0.0, 0.0, 0.0),
       ('070816e8-0e76-432d-995f-700534b28f24', 31, 2, 'empty', 0.0, 0.0, 0.0),
       ('e008978e-22b2-11ed-a2ff-cbfae0543424', 44, 4, 'empty', 0.0, 0.0, 0.0),
       ('070816e8-0e76-432d-995f-700534b28f24', 17, 5, 'empty', 0.0, 0.0, 0.0);

INSERT INTO favorites_food(rate, text)
VALUES (5, 'Meat'),
       (4, 'Cheese with'),
       (4, 'Pizza with pork and cheese and peppers, spacy'),
       (1, 'Spicy crab cakes'),
       (3, 'Pan seared steak'),
       (2, 'Chicken fried steak with cheesy mash'),
       (5, 'Roast chicken with vegetables and wild rice'),
       (3, 'Candied almond and blue cheese rocket salad'),
       (1, 'White Chocolate and Pistachio Cheesecake'),
       (3, 'Pork chops with spicy orange marmalade'),
       (4, 'Roast beef with a herb and malt crust'),
       (2, 'Grilled salmon tacos'),
       (5, 'Ham and mustard crisps'),
       (3, 'Walnut and goat''s cheese salad with pomegranate dressing');
