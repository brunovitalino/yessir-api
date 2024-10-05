ALTER TABLE usuarios ADD COLUMN mesa_id integer;
ALTER TABLE usuarios ADD FOREIGN KEY(mesa_id) REFERENCES mesas(id);
