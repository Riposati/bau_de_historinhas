-- Banco Baú de histórinhas

create table historias(
 id serial primary key,
 foto varchar(5000),
 nome varchar(50),
 historia varchar(100000),
 autor varchar(50),
 indicada int
);


create table usuario(
 id serial primary key ,
 nomeusuario varchar(50),
 senha varchar(50),
 email varchar(500)
);


create table auxiliar_usuario_historia(
 historia_id int ,
 usuario_id int,
 primary key(historia_id,usuario_id),
 foreign key(historia_id) references historias(id),
 foreign key(usuario_id) references usuario(id)
);


create table historiasLidas(
	id serial primary key,
	lidas boolean,
	foreign key(id)references historias(id)
);

