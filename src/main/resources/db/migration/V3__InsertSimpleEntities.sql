insert into customer(name, discount_card)
    values ('Dmitry Cool', false),
           ('Andrey Kot', true),
           ('Alex Meow', true),
           ('Alisa Cute', false),
           ('Misha Kotov', true),
           ('Irina Yareshko', true),
           ('Daria Pushka', false);

insert into supplier(organization)
values ('ABC'),
       ('Best supplier'),
       ('Qwerty'),
       ('NikonOfficial'),
       ('CanonOfficial');

insert into organization(address, branch_office_adress)
values ('Pirogova 5', null),
       ('Pirogova 3', 'Pirogova 5'),
       ('Odojevskogo 1/2', null),
       ('Berezovaja 13', 'Odojevskogo 1/2'),
       ('Lenina 68', null),
       ('Lenina 6', 'Lenina 68');

