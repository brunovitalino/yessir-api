ALTER TABLE cardapios ADD COLUMN preco numeric(12,2);
UPDATE cardapios SET preco = 0.0;
ALTER TABLE cardapios ALTER COLUMN preco SET NOT NULL;
ALTER TABLE cardapios ALTER COLUMN preco SET DEFAULT 0.0;
