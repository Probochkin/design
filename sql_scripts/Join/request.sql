select * from department d left join employees e on d.id = e.department_id;
select * from department d right  join employees e on d.id = e.department_id;
select * from department d full join employees e on d.id = e.department_id;
select * from department d cross join employees e;

select d.name from department d left join employees e on d.id = e.department_id
where e.department_id is null;

select d.name,e.name from department d right  join employees e on d.id = e.department_id;

select d.name,e.name  from department d left join employees e on d.id = e.department_id
where e.department_id is not null;
