create table if not exists company(
	id int primary key, 
	s_name varchar(60)
);
create table if not exists supplier(
	id int primary key, 
	id_company int, 
	foreign key(id_company) references company(id), 
	s_name varchar(60)
);
create table if not exists customer(
	id int primary key, 
	id_company int, 
	foreign key(id_company) references company(id), 
	s_name varchar(60)
);

insert into company  values(0, 'хлебозавод');
insert into supplier values(0, 0, 'мукомольная фабрика');
insert into supplier values(1, 0, 'тарный комбинат');
insert into customer values(0, 0, 'кондитерский магазин');
insert into customer values(1, 0, 'булочная');