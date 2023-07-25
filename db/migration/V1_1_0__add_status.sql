CREATE TABLE IF NOT EXISTS status
(id integer PRIMARY KEY,
status_name text NOT NULL,
description text NOT NULL);

INSERT INTO status(id, status_name, description) VALUES(1,'NEW','Новый');
INSERT INTO status(id, status_name, description) VALUES(2,'OLD','Старый');
INSERT INTO status(id, status_name, description)  VALUES(3,'CANCELED','Удаленный');