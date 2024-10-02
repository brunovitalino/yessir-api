CREATE TABLE cardapio_tipo(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL UNIQUE,
	ativo BOOLEAN DEFAULT 't',
	created TIMESTAMP NOT NULL,
	updated TIMESTAMP NOT NULL
);

INSERT INTO cardapio_tipo(nome, created, updated) VALUES('COMIDA', now(), now());

CREATE TABLE cardapio_icone(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL UNIQUE,
	ativo BOOLEAN DEFAULT 't',
	created TIMESTAMP NOT NULL,
	updated TIMESTAMP NOT NULL
);

INSERT INTO cardapio_icone(nome, created, updated) VALUES('local_dining', now(), now());

ALTER TABLE cardapios ADD COLUMN cardapio_tipo_id integer NOT NULL DEFAULT 1, ADD FOREIGN KEY(cardapio_tipo_id) REFERENCES cardapio_tipo(id);
ALTER TABLE cardapios ADD COLUMN cardapio_icone_id integer NOT NULL DEFAULT 1, ADD FOREIGN KEY(cardapio_icone_id) REFERENCES cardapio_icone(id);
