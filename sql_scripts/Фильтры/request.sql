select * from product 
as p join type as t on p.type_id =t.id where t.name='Сыр';

select *
from product as p join type as t on p.type_id =t.id where t.name='Мороженое';

select *
from product where name like '%Мороженое%';

select *
from product where expired_date <  current_date;

select  max(price), name 
from product
 GROUP BY name
 ORDER BY max(price) DESC
 LIMIT 1;

select t.name,COUNT(p.name) from type 
as t join product as p on t.id = p.type_id 
group by t.name;

select p.name from type 
as t join product as p on t.id = p.type_id where t.name = 'Сыр' or t.name = 'Молоко'
;

select t.name from type 
as t join product as p on t.id = p.type_id 
group by t.name
having COUNT(p.name) < 10;

select p.name as "продукт" ,t.name as "тип продукта" from type 
as t join product as p on t.id = p.type_id 
;