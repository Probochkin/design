create table videoCards(
    id serial primary key, 
    name text,
    releaseDate date,
    memoryFrequency smallint
);
insert into videoCards (name,releaseDate,memoryFrequency) values ('GeForce RTX 3090','2022/01/21', 19500);
select * from videoCards;
update videoCards set name = 'GeForce RTX 3090 TI';
select * from videoCards;
delete from videoCards;
select * from videoCards;