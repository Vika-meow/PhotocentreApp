insert into worker(name, profile, address)
values ('Andrey Borisov', 'Photographer', 'Pirogova 5'),
       ('Alexander Krih', 'Manager', 'Pirogova 5'),
       ('Arina Chramova', 'Seller', 'Pirogova 5'),
       ('Karina Semova', 'Seller', 'Pirogova 3'),
       ('Ksenia Dukalis', 'Manager', 'Pirogova 3'),
       ('Tanya Petrova', 'Photographer', 'Odojevskogo 1/2'),
       ('Katerina Kemova', 'Seller', 'Odojevskogo 1/2'),
       ('Sergey Memov', 'Manager', 'Odojevskogo 1/2'),
       ('Anya Kobra', 'Seller', 'Berezovaja 13'),
       ('Fedor Guskov', 'Manager', 'Berezovaja 13'),
       ('Liza Malysheva', 'Photographer', 'Lenina 68'),
       ('Nastya Davydova', 'Manager', 'Lenina 68'),
       ('Liza Repnikova', 'Seller', 'Lenina 68'),
       ('Serafima Desyatnichenko', 'Manager','Lenina 6'),
       ('Lidia Kosteckaja', 'Seller','Lenina 6');

insert into delivery(date, address, supplier_id)
values ('20.05.2020', 'Pirogova 5', 1),
       ('28.05.2020', 'Pirogova 3', 2),
       ('23.05.2020', 'Odojevskogo 1/2', 3),
       ('30.05.2020', 'Berezovaja 13', 4),
       ('01.06.2020', 'Lenina 68', 5),
       ('06.06.2020', 'Lenina 6', 1),
       ('29.05.2020', 'Pirogova 5', 4),
       ('23.05.2020', 'Odojevskogo 1/2', 5);


insert into check_entity(date, name, address)
values ('20.05.2020', 'Dmitry Cool' ,'Pirogova 5'),
       ('28.05.2020', 'Andrey Kot', 'Pirogova 3'),
       ('23.05.2020', 'Alisa Cute','Odojevskogo 1/2'),
       ('30.05.2020', 'Alex Meow','Berezovaja 13'),
       ('01.06.2020', 'Misha Kotov','Lenina 68'),
       ('06.06.2020', 'Irina Yareshko','Lenina 6'),
       ('29.05.2020', 'Daria Pushka' ,'Pirogova 5'),
       ('23.05.2020', 'Andrey Kot' ,'Odojevskogo 1/2');