create table person(
	id integer auto_increment not null,
	name varchar(255) not null,
	location varchar(255),
	birth_date timestamp,
	primary key(id)
);

insert into person (name, location, birth_date) values ('Jhon Snow', 'EUA', SYSDATE);
insert into person (name, location, birth_date) values ('Mario Dantas', 'BR', SYSDATE); 
insert into person (name, location, birth_date) values ('Camilo Silva', 'BR', SYSDATE); 
insert into person (name, location, birth_date) values ('Ana Barbosa', 'BR', SYSDATE);
insert into person (name, location, birth_date) values ('Ana Silva', 'BR', SYSDATE);  
insert into person (name, location, birth_date) values ('Augusto', 'BR', SYSDATE);  