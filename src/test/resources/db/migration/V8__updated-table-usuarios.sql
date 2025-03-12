ALTER TABLE usuarios RENAME COLUMN login TO email;
ALTER TABLE usuarios ADD COLUMN nome VARCHAR(255);
ALTER TABLE usuarios ADD COLUMN created TIMESTAMP;
ALTER TABLE usuarios ADD COLUMN updated TIMESTAMP;

UPDATE usuarios SET nome = '';
UPDATE usuarios SET created = current_timestamp;
UPDATE usuarios SET updated = current_timestamp;

ALTER TABLE usuarios ALTER COLUMN nome SET NOT NULL;
ALTER TABLE usuarios ALTER COLUMN created SET NOT NULL;
ALTER TABLE usuarios ALTER COLUMN updated SET NOT NULL;
