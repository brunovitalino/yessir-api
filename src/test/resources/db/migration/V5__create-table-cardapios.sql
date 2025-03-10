create table cardapios(
	id SERIAL,
	nome VARCHAR(100) NOT NULL,
	estabelecimento_id integer NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(estabelecimento_id) REFERENCES estabelecimentos(id)
);
