create table body (
    id serial primary key,
    name varchar(30)
);

create table engine (
     id serial primary key,
     name varchar(30)
);

create table gearbox (
      id serial primary key,
      name varchar(30)
);

create table cars (
     id serial primary key,
     name varchar(30),
     model varchar(30),
     body_id int references body(id),
     engine_id int references engine(id),
     gearbox_id int references gearbox(id)
);

insert into body(name) values
       ('Хечбэк'), ('Купе'),
       ('Пикап'),('Седан');

insert into engine(name) values
       ('V4'), ('V6'), ('V8'), ('V10'), ('V12');

insert into gearbox(name) values ('Механическая'), ('Автомат'), ('Робот');

insert into cars(name, model, body_id, engine_id, gearbox_id) values
('Chevrolet', 'Модель 1', 2, 1, 2), ('Opel', 'Модель 1', 1, 2, 1),
('Volkswagen', 'Модель 1', 1, 2, 1), ('Ford', 'Модель 1', 3, 1, 1),
('Audi', 'Модель 1', 1, 2, 2), ('Nissan', ' Модель 1', 1, 1, 1),
('Bmw', 'Модель 1', 2, 2, 2), ('Mersedes', 'Модель 1', 1, 2, 1),
('Peugeot', 'Модель 1', 2, null, 1), ('Toyota', 'Модель 1', 1, 2, null),
('Lexus', 'Модель 1', null, null, null);

select auto.name, auto.model, b.name, e.name, g.name
from cars as auto
left join body b on auto.body_id = b.id
left join engine e on e.id = auto.engine_id
left join gearbox g on g.id = auto.gearbox_id;

select body.name from cars right join body on body.id = cars.body_id
where cars.model is null;

select engine.name from engine left join cars on engine.id = cars.engine_id
where cars.name is null;

select gearbox.name from cars right join gearbox on gearbox.id = cars.gearbox_id
where cars.model is null;