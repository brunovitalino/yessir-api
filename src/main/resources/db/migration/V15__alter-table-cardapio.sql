CREATE TABLE cardapio_tipo(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO cardapio_tipo(nome) VALUES('COMIDA');

CREATE TABLE cardapio_icone(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO cardapio_icone(nome) VALUES('local_dining');

ALTER TABLE cardapios ADD COLUMN cardapio_tipo_id integer NOT NULL DEFAULT 1, ADD FOREIGN KEY(cardapio_tipo_id) REFERENCES cardapio_tipo(id);
ALTER TABLE cardapios ADD COLUMN cardapio_icone_id integer NOT NULL DEFAULT 1, ADD FOREIGN KEY(cardapio_icone_id) REFERENCES cardapio_icone(id);
