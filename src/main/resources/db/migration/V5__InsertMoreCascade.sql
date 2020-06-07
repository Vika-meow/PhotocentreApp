insert into delivery_input(delivery_id, goods_id, count)
values (1, 1, 10),
       (1, 2, 10),
       (1, 4, 15),
       (1, 6, 15),
       (2, 3, 5),
       (2, 2, 5),
       (3, 4, 15),
       (3, 7, 6),
       (4, 6, 30),
       (5, 5, 5),
       (5, 6, 20),
       (6, 3, 7),
       (6, 5, 8),
       (7, 1, 6),
       (7, 4, 30),
       (7, 6, 25),
       (8, 2, 2),
       (8, 6, 17);

insert into item(check_id, goods_id, order_id, service_id, count, urgency)
values (1, 1, null, null, 1, false),
       (1, null, 1, null, 30, false),
       (1, null, null, 1, 1, false),
       (2, 4, null, null, 2, false),
       (2, 6, null, null, 2, false),
       (2, 7, null, null, 1, false),
       (3, 2, null, null, 1, false),
       (3, null, 2, null, 10, false),
       (3, 5, null, null, 1, false),
       (4, null, null, 5, 3, false),
       (5, 3, null, null, 1, false),
       (5, null, null, 3, 2, false),
       (5, 6, null, null, 2, false),
       (5, null, 2, null, 30, false),
       (6, 2, null, null, 1, false),
       (6, null, 4, null, 30, false),
       (7, 1, null, null, 1, false),
       (7, 3, null, null, 1, false),
       (7, null, 4, null, 30, false),
       (7, null, null, 5, 5, false),
       (8, 4, null, null, 4, false),
       (8, null, 2, null, 20, false),
       (8, null, 5, null, 10, false);




