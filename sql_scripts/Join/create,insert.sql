create table department(
    id serial primary key,
    name varchar(255)
);
create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references department(id)
)

insert into department(name) values ('department 1');
insert into department(name) values ('department 2');
insert into department(name) values ('department 3');
insert into department(name) values ('department 4');
insert into employees(name,department_id) values ('Иванов',1),('Петров',2),('Сидоров',3),('Иванова',1);


