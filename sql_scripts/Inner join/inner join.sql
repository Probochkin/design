select * from faculties as fac join teachers as teach on fac.id = teach.faculty_id;
select name as "Предмет",teach.FIO as "ФИО" from faculties as fac join teachers as teach on fac.id = teach.faculty_id;
select name as "Предмет",teach.FIO as "ФИО", fac.academic_hours as "Академических часов" from faculties as fac join teachers as teach on fac.id = teach.faculty_id where teach.FIO like 'Сорокин';






