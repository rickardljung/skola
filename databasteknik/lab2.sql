-- Delete the tables if they exist. Set foreign_key_checks = 0 to
-- disable foreign key checks, so the tables may be dropped in
-- arbitrary order.
set foreign_key_checks = 0;
drop table if exists Users;
...
set foreign_key_checks = 1;

create table users (
userName varchar(20),
name varchar(20),
address varchar(20),
telNbr varchar(20),
primary key (userName));

create table theater (
name varchar(20),
nbrSeats int,
primary key (name));

create table movies (
name varchar(20),
primary key(name));

create table reservations (
resNbr integer not null auto_increment, 
userName varchar(20), 
date date, 
movieName varchar(20), 
primary key (resNbr), 
foreign key (userName) references users(userName), 
foreign key (date, movieName) references performances(date, movieName));

create table performances ( 
date date(20), 
movieName varchar(20), 
theaterName varchar(20), 
availableTickets int, 
primary key(date, movieName), 
foreign key (movieName) references movies(name), 
foreign key (theaterName) references theaters(name));

insert into users values('Perry', 'Erik', 'Lund', '112'), ('Rille', 'Rickard', 'Vänersborg', '1337');

insert into theaters values('SF', 300), ('Eriks soffa', 5);

insert into movies values('Harry Potter'), ('The Room'), ('Batman');

insert into performances values('2015-02-03', 'Harry Potter', 'SF', select nbrSeats from theaters where name = 'SF');

insert into performances values('2015-02-05', 'The Room', 'Eriks soffa', select nbrSeats from theaters where name = 'Eriks soffa')

insert into reservations(userName, date, movieName) values('Perry', '2015-02-03', 'Harry Potter');

insert into reservations(userName, date, movieName) values('Rille', '2015-02-05', 'The Room');



insert into theaters values('SF', 300);

insert into performances values('2015-02-03', 'Harry Potter', 'SF', select nbrSeats from theaters where name = 'SF');

insert into performance values('2015-02-03', 'Harry Potter', 'SF', select nbrSeats from theaters where name = 'Hasses källare');

insert into reservations(userName, date, movieName) values('HasseEriksson', '2015-02-03', 'Harry Potter');