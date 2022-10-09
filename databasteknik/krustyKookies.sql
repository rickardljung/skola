set foreign_key_checks = 0;

drop table if exists Customers;
drop table if exists Ingredients;
drop table if exists CookieTypes;
drop table if exists Recipes;
drop table if exists Pallets;
drop table if exists Deliveries;
drop table if exists Orders;
drop table if exists OrderCookieTypes;

set foreign_key_checks = 1;


create table Customers (
	pNbr char(11),
	name varchar(30),
	address varchar(30),
	primary key (pNbr)
);

create table Ingredients (
	name varchar(30),
	totalAmount int,
	primary key (name)
);

create table CookieTypes (
	name varchar(20),
	primary key (name)
);

create table Recipes (
	ingredientName varchar(30),
	cookieName varchar(20),
	amount int,
	primary key (ingredientName, cookieName),
	foreign key (ingredientName) references ingredients(name),
	foreign key (cookieName) references CookieTypes(name)
);

create table Orders (
	orderId integer not null auto_increment,
	customerPnbr varchar(11), 
	nbrOfPallets int,
	orderDatetime date,
	primary key (orderId),
	foreign key (customerPnbr) references Customers(pNbr)
);

create table Deliveries (
	deliveryId integer not null auto_increment,
	orderId int,
	deliveryDateTime date,
	primary key (deliveryId),
	foreign key (orderId) references Orders(orderId)
);

create table Pallets (
	palletNbr integer not null auto_increment,
	cookieName varchar(20),
	deliveryId int,
	prodDate date,
	isOkay bool,
	primary key (palletNbr),
	foreign key (cookieName) references CookieTypes(name),
	foreign key (deliveryId) references Deliveries(deliveryId)
);
	
create table OrderCookieTypes (
	cookieName varchar(20),
	orderId int,
	primary key (cookieName, orderId),
	foreign key (cookieName) references CookieTypes(name),
	foreign key (orderId) references Orders(orderId)
);


insert into Customers values("920918-2279", "Rickard Johansson", "Kämnärs Lund");
insert into Customers values("123456-1337", "Nisse Bengtsson", "Korvgatan Malmö");

insert into Ingredients values("Flour", 20000);
insert into Ingredients values("Butter", 15000);
insert into Ingredients values("Icing sugar", 10000);
insert into Ingredients values("Roasted, chopped nuts", 10000);
insert into Ingredients values("Fine-ground nuts", 20000);
insert into Ingredients values("Ground, roasted nuts", 20000);
insert into Ingredients values("Bread crumbs", 30000);
insert into Ingredients values("Sugar", 50000);
insert into Ingredients values("Egg whites", 1000);
insert into Ingredients values("Chocolate", 20000);

insert into CookieTypes values("Nut Ring");
insert into CookieTypes values("Nut Cookie");

