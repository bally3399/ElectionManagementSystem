truncate table admin cascade;
truncate table candidates cascade;
truncate table elections cascade;
truncate table voters cascade;
truncate table votes cascade;

insert into admin(id, email,first_name, last_name, password) values
(100, 'admin1@email.com', 'john','doe', 'password'),
(101, 'admin2@email.com','jane', 'doe', 'password');

insert into voters(id, voter_number, first_name, last_name, is_locked, date_of_birth, state_of_origin, building_number,street_name, ward, city, local_government_area, state) values
(200, '100000', 'Adamu', 'Baba', 'false', '1/1/1991', 'Kano', '1', 'adedoyin', 'Ikateland', 'Lekki', 'Eti-Osa', 'Lagos'),
(201, '100001', 'Chike', 'Dalu', 'false', '2/2/1992', 'Anambra', '2', 'bode', 'Igbobi', 'Yaba', 'Lagos Mainland', 'Lagos'),
(202, '100002', 'Fola', 'Gbenga', 'false', '3/3/1993', 'Ogun', '3', 'allen', 'Isiu', 'Ikorodu', 'Ikorodu', 'Lagos'),
(203, '100003', 'Efosa', 'Edosa', 'false', '4/4/1995', 'Edo', '4', 'fayemi', 'Ikate', 'Surulere', 'Surulere', 'Lagos'),
(204, '100004', 'Akpan', 'Ekong', 'false', '5/5/1995', 'Cross-river', '5', 'Oguntola', 'Odomola', 'Lekki', 'Epe', 'Lagos');

insert into elections(election_id, category, title, location, start_date, start_time, end_date, end_time, is_registration_open, admin_id, election_status) values
(300, 'NATIONAL', 'Ogun state Governorship election', 'Ogun', '9/1/2024', '7:00', '9/5/2024', '23:00', true, 100, 'NOT_STARTED'),
(301, 'NATIONAL', 'Ondo state Governorship election', 'Ondo', '9/1/2024', '7:00', '9/5/2024', '23:00', true, 100, 'NOT_STARTED'),
(302, 'NATIONAL', 'Osun state Governorship election', 'Osun', '9/1/2024', '7:00', '9/5/2024', '23:00', true, 100, 'NOT_STARTED'),
(303, 'NATIONAL', 'Ekiti state Governorship election', 'Osun', '9/1/2024', '7:00', '9/5/2024', '23:00', true, 100, 'NOT_STARTED');

insert into candidates(id, first_name, last_name, biography,date_of_birth,email, phone_number, position_contested, party, is_suspended) values
(400, 'Ade', 'Babalola', 'Ade Babalola was born in 1970.', '1/1/1970', 'adebabalola@gmail.com', '08012345678', 'Governor', 'PDP', 'false'),
(401, 'Dele', 'Eyitope', 'Dele Eyitope was born in 1971.', '2/2/1971','deleeyitope@gmail.com', '09012345678', 'Governor', 'APC', 'false');
