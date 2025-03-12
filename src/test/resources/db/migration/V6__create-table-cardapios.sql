DROP TABLE cardapios;
CREATE TABLE cardapios(
	id SERIAL,
	estabelecimento_id integer NOT NULL,
	nome VARCHAR(100) NOT NULL,
	ativo BOOLEAN,
	PRIMARY KEY(id),
	FOREIGN KEY(estabelecimento_id) REFERENCES estabelecimentos(id)
);
