insert into advisor (email, username, first_name, last_name, role)
values ('advisor@mail.com', 'username', 'first_name', 'last_name', 'PARTNER');

insert into applicant (email, username, first_name, last_name, ssn, city, street, number, zip, apt)
values ('email', 'username', 'first_name', 'last_name', 'ssn', 'city', 'street', 'number', 'zip', 'apt');

insert into application (advisor_id, applicant_id, amount_of_money, status)
values (1, 1, 15000, 'NEW'),
       (1, 1, 1500, 'APPROVED');