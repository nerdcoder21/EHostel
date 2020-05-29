create table Hostel (
hostelId int,
hostelName varchar (30),
hostelRooms int,
primary key (hostelId)
);

create table Warden(
warden varchar (30),
hostelId int,
foreign key (hostelId) references Hostel(hostelId)
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
hostelId int,
room int,
accNo varchar (30),
ifsc varchar (20),
primary key (registrationNo),
foreign key (hostelId) references Hostel(hostelId)
);



insert into hostel values (1, 'SVBH', 350);