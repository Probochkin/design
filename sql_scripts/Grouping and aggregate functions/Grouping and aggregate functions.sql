select avg(price) 
from devices;

select avg(d.price), p.name
from devices_people as d_p join devices as d on d_p.device_id =d.id join people  as p on  d_p.people_id =p.id
group by d_p.people_id,p.name;

select avg(d.price), p.name
from devices_people as d_p join devices as d on d_p.device_id =d.id join people  as p on  d_p.people_id =p.id
group by d_p.people_id,p.name
having avg(d.price) > 5000;