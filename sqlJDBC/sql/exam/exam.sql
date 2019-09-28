CREATE TABLE IF NOT EXISTS cities (
id SERIAL PRIMARY KEY,
name VARCHAR(50)
);

INSERT INTO cities (name)
VALUES
('Москва'),
('Москва'),
('СПБ'),
('Казань'),
('Казань'),
('Новгород');

CREATE INDEX name_index ON cities(name);

ALTER TABLE cities
ADD id_city SERIAL;

DELETE FROM cities
WHERE cities.id_city > (SELECT MIN(t.id_city)
FROM cities AS t WHERE t.name=cities.name);

ALTER TABLE cities
DROP COLUMN id_city;