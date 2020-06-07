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



insert into hostel values (1, 'SVBH', 350);