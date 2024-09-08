CREATE TABLE roles(
	id SERIAL,
	nome VARCHAR(100) NOT NULL UNIQUE,
	ativo BOOLEAN DEFAULT 't',
	created TIMESTAMP NOT NULL,
	updated TIMESTAMP NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE usuarios_roles (
	usuario_id BIGINT NOT NULL,
	roles_id BIGINT NOT NULL,
	FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
	FOREIGN KEY (roles_id) REFERENCES "roles"(id)
);
