create table faculties(
    id serial primary key,
    name varchar(255),
    academic_hours int
);
create table teachers(
    id serial primary key,
    FIO varchar(255),
    faculty_id int references faculties(id)
);
