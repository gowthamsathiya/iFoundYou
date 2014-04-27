create table usertable(useremail varchar(50) primary key, name varchar(50),password varchar(50));

create table locationtable(locationid varchar(50) primary key, locationname varchar(100));

create table userlocation(useremail varchar(50) primary key, lastlocation varchar(50) ,  lasttimefound varchar(20), foreign key(useremail) references usertable(useremail), foreign key(lastlocation) references locationtable(locationid));

create table friendstable(useremail varchar(50), friendemail varchar(50), foreign key(useremail) references usertable(useremail), foreign key(friendemail) references usertable(useremail));