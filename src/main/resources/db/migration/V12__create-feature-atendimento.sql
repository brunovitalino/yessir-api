CREATE TABLE atendentes(
	id SERIAL,
	nome VARCHAR(100) NOT NULL UNIQUE,
	ativo BOOLEAN DEFAULT 't',
	created TIMESTAMP NOT NULL,
	updated TIMESTAMP NOT NULL,
	PRIMARY KEY(id)
);

CREATE TYPE atendimento_status AS ENUM('AGUARDANDO', 'EM_ATENDIMENTO', 'ENCERRADO');

CREATE TABLE atendimentos(
	id SERIAL,
	mesa_id integer NOT NULL,
	atendente_id integer NOT NULL,
	status atendimento_status NOT NULL,
	ativo BOOLEAN DEFAULT 't',
	created TIMESTAMP NOT NULL,
	updated TIMESTAMP NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(mesa_id) REFERENCES mesas(id),
	FOREIGN KEY(atendente_id) REFERENCES atendentes(id)
);
