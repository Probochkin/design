create table studentTicket(
    id serial primary key,
    course int
);

create table student(
    id serial primary key,
    FIO varchar(255),
    studentTicket_id int references studentTicket(id) unique
);
