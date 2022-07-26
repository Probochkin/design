create table teens (
    id serial primary key,
    name varchar(30),
    gender varchar(2)
);

insert into teens (name, gender) values
('Петр', 'M'), ('Екатерина', 'W'), ('Ольга', 'W'),
('Николай', 'M'), ('Александра', 'W'), ('Дмитрий', 'M');

select n1.name || ' и ' || n2.name as pair from teens as n1
cross join teens as n2 where n1.gender <> n2.gender;