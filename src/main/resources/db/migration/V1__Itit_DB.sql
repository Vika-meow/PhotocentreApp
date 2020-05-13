create sequence hibernate_sequence start 1 increment 1;

create table check_entity (
    check_id serial not null,
    date date,
    name varchar(255),
    address varchar(255),
    primary key (check_id));

create table customer (
    name varchar(255) not null,
    discount_card boolean not null,
    primary key (name));

create table delivery(
    delivery_id serial not null,
    date date,
    address varchar(255),
    supplier_id int4,
    primary key (delivery_id));

create table delivery_input(
    delivery_input_id serial not null,
    count int4 not null,
    delivery_id int4,
    goods_id int4,
    primary key (delivery_input_id));

create table goods_price(
    goods_id serial not null,
    buy_price int4 not null,
    company varchar(255),
    goods_model varchar(255),
    name_of_goods varchar(255),
    sell_price int4 not null,
    primary key (goods_id));

create table item(
    item_id serial not null,
    count int4 not null,
    urgency boolean not null,
    check_id int4,
    goods_id int4,
    order_id int4,
    service_id int4,
    primary key (item_id));

create table order_price
    (order_id serial not null, format varchar(255), order_type varchar(255),
    paper_type varchar(255), price int4 not null, primary key (order_id));

create table organization
    (address varchar(255) not null, branch_office_adress varchar(255), primary key (address));

create table service_price
    (service_id serial not null, price int4 not null,
    service_type varchar(255), primary key (service_id));

create table supplier
    (supplier_id serial not null, organization varchar(255), primary key (supplier_id));

create table worker
    (name varchar(255) not null, profile varchar(255), address varchar(255), primary key (name));

alter table if exists check_entity
    add constraint FKopjucbas01lgojuxoqcpnvb4l foreign key (name) references customer;

alter table if exists check_entity
    add constraint FKompt97gj9vvqu0xb0bl5yqe35 foreign key (address) references organization;

alter table if exists delivery
    add constraint FKck92wwcnd9jl7q8brvy9htdmk foreign key (address) references organization;

alter table if exists delivery
    add constraint FK3pvccpw13vordr1meuof4vh7 foreign key (supplier_id) references supplier;

alter table if exists delivery_input
    add constraint FK3n3otoojdp5lokc7vqmvvmwyn foreign key (delivery_id) references delivery;

alter table if exists delivery_input
    add constraint FKafoihmn2f5gagxq432yhhmomf foreign key (goods_id) references goods_price;

alter table if exists item
    add constraint FK5u0lxrvcok5amcf654i9rjyt2 foreign key (check_id) references check_entity;

alter table if exists item
    add constraint FKchwfw6ybrbogirseq7eehohui foreign key (goods_id) references goods_price;

alter table if exists item
    add constraint FK31o7ngtimru8h4tcf46ppuo0i foreign key (order_id) references order_price;

alter table if exists item
    add constraint FK2kpqjhiny3791tcejk24a0qk8 foreign key (service_id) references service_price;

alter table if exists organization
    add constraint FK1cdadel66gavu3widudjjvofs foreign key (branch_office_adress) references organization;

alter table if exists worker
    add constraint FKs073so80uxb763cmw4gvubs35 foreign key (address) references organization;