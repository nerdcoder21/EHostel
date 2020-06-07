create table Hostel (
id int,
name varchar (30),
rooms int,
primary key (id)
);

create table Warden(
warden varchar (30),
id int,
foreign key (id) references Hostel(id)
);


create table User (
username varchar (30),
password varchar (50),
role varchar (20),
primary key (username)
);


create table Student (
registrationNo VARCHAR (30),
name varchar (30),
dob date,
course varchar (30),
semester int,
id int,
room int,
accNo varchar (30),
ifsc varchar (20),
primary key (registrationNo),
foreign key (id) references Hostel(id)
);




insert into hostel values (1, 'SVBH Hostel', 3, 350);
insert into hostel values (2, 'PG Boys Hostel', 2, 185);
insert into hostel values (3, 'Tilak Hostel', 2, 338);
insert into hostel values (4, 'Raman Hostel', 4, 90);
insert into hostel values (5, 'Tandon Hostel', 2, 283);
insert into hostel values (6, 'Malviya Hostel', 2, 283);
insert into hostel values (7, 'Tagore Hostel', 4, 123);
insert into hostel values (8, 'Patel Hostel', 2, 338);
insert into hostel values (9, 'KN Girls Hostel', 3, 243);
insert into hostel values (10, 'PG Girls Hostel', 3, 33);