ALTER TABLE atendimentos RENAME COLUMN status TO status_old;
ALTER TABLE atendimentos ADD COLUMN status VARCHAR(100) NOT NULL DEFAULT 'AGUARDANDO';
UPDATE atendimentos SET status = cast(status_old AS VARCHAR);
ALTER TABLE atendimentos DROP COLUMN status_old;
