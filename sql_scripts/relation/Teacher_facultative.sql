 create table Teacher(
     id serial primary key,
     FIO varchar(255)
 );
 
 create table facultative(
     id serial primary key,
     name varchar(255)
 );
 
 create table Teacher_facultative(
     id serial primary key,
     Teacher_id int references Teacher(id),
     facultative_id int references facultative(id)
 );