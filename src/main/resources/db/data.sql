truncate table admin cascade;
truncate table ballot cascade;
truncate table candidates cascade;
truncate table elections cascade;
truncate table voters cascade;
truncate table votes cascade;

insert into elections(election_id, category, title, location, start_date, start_time, end_date, end_time, is_registration_open) values
(100, 'NATIONAL', 'Ogun state Governorship election', 'Ogun', '1/9/2024', '7:00', '5/9/2024', '23:00', true),
(101, 'NATIONAL', 'Ondo state Governorship election', 'Ondo', '1/9/2024', '7:00', '5/9/2024', '23:00', true),
(102, 'NATIONAL', 'Osun state Governorship election', 'Osun', '1/9/2024', '7:00', '5/9/2024', '23:00', true);

inser into admin(id, email, password, username) values
(200, 'admin@email.com', 'password', 'admin')