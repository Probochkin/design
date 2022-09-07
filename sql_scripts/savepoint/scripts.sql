set session characteristics as transaction isolation level serializable;
begin transaction;
savepoint first;
insert into products (name, producer, count, price) VALUES ('product_6', 'producer_6', 11, 64);
select * from products;
rollback to first;
select * from products;
commmin;

begin transaction;
select * from products;
savepoint first;
insert into products (name, producer, count, price) VALUES ('product_6', 'producer_6', 11, 64);
savepoint next;
select * from products;
rollback to first;
select * from products;
rollback to next;