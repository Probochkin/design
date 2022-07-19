create table departments(
    id serial primary key,
    name varchar(255)   
);
create table teachers(
    id serial primary key,
    FIO varchar(255),
    department_id int references departments(id)
);



 