ALTER TABLE estabelecimentos ADD COLUMN ativo BOOLEAN;
UPDATE estabelecimentos SET ativo = 't';
ALTER TABLE estabelecimentos ALTER COLUMN ativo SET NOT NULL;
ALTER TABLE estabelecimentos ALTER COLUMN ativo SET DEFAULT TRUE;
