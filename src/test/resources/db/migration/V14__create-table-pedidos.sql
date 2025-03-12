CREATE TABLE pedidos(
	id SERIAL,
	atendimento_id integer NOT NULL,
	cardapio_id integer NOT NULL,
	quantidade integer NOT NULL,
	ativo BOOLEAN DEFAULT 't',
	created TIMESTAMP NOT NULL,
	updated TIMESTAMP NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(atendimento_id) REFERENCES atendimentos(id),
	FOREIGN KEY(cardapio_id) REFERENCES cardapios(id)
);
