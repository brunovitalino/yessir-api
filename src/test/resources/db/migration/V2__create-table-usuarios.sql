create table usuarios(
	id SERIAL,
	login VARCHAR(100) NOT NULL unique,
	senha VARCHAR(255) NOT NULL,
	PRIMARY KEY(id)
);
