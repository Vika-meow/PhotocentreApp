insert into goods_price
    (buy_price, company, goods_model, name_of_goods, sell_price)
    values (3000, 'Canon', 'x555', 'Photocamera', 7000),
           (5000, 'Canon', 'y777', 'Photocamera', 12000),
           (4000, 'Canon', 'vv5', 'Videocamera', 12000),
           (150, 'Canon', 'w2q', 'Film', 450),
           (8000, 'Nikon', 'cf12', 'Photocamera', 15000),
           (200, 'Nikon', 'xx2', 'Film', 500),
           (12000, 'Nikon', 'of4', 'Videocamera', 17000);


insert into service_price
(service_type, price)
values ('Document photo', 300),
       ('Restoration of photo', 1500),
       ('Camera Rent', 500),
       ('Professional photo shoot', 5000),
       ('Draw a cat on photo', 800);

insert into order_price
(format, order_type, paper_type, price)
values ('A5', 'Print photo', 'gloss', 50),
       ('A4', 'Print photo', 'gloss', 150),
       ('A3', 'Print photo', 'gloss', 300),
       ('A5', 'Print photo', 'matte', 40),
       ('A4', 'Print photo', 'matte', 130),
       ('A3', 'Print photo', 'matte', 300),
       (null, 'Develop a film', null, 50);