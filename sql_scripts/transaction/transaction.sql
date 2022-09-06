create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);
insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

--read committed

transaction1

begin transaction;

select * from products;

insert into products (name, producer, count, price) values ('product_4','producer_4', 35, '150');

delete from products where price  = 50;

select * from products;

update products set name = 'producer_22' where count = 35;

select * from products;

commit;


transaction2

begin transaction;

select * from products;

select * from products;

select * from products;

commit;



--repeatable read
transaction1

begin transaction isolation level repeatable read;

select * from products;

insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 11, 64);

delete from products where price = 115;

update products set price = 75 where name = 'product_3';

rollback

transaction2

begin transaction isolation level repeatable read;

select * from products;

update products set price = 75 where name = 'product_3';

commit;



--serializable
transaction1
begin transaction isolation level serializable;
select * from products;
update products set count = 26 where name = 'product_2';
select avg(age) from person;
update person set age = 21 where name = 'person_33';
commit;
eror1

begin transaction isolation level serializable;
update products set count = 26 where name = 'product_4';
commit;
eror2


transaction2

begin transaction isolation level serializable;
select * from products;
update products set count = 26 where name = 'product_3';
select * from products;
commit;
eror1

begin transaction isolation level serializable;
update products set count = 26 where name = 'product_4';
eror2
