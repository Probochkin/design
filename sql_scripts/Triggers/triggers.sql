create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);
--1 Триггер должен срабатывать после вставки данных, для любого товара и просто насчитывать налог на товар.
-- Действовать он должен не на каждый ряд, а на запрос (statement уровень)
create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';
create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();
--2 Триггер должен срабатывать до вставки данных и высчитывать налог на товар. Здесь используем row уровень.

create or replace function tax_row()
    returns trigger as
$$
    BEGIN
        new.price = new.price / 1.2;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_row
    before insert
    on products
    for each row
    execute procedure tax_row();
--3 Нужно написать триггер на row уровне, который при вставке продукта в таблицу products,
-- будет заносить имя, цену и текущую дату в таблицу history_of_price.


create or replace function history_price()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date)
	    VALUES (new.name, new.price, CURRENT_DATE);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger history
    after insert
    on products
    for each row
    execute procedure history_price();