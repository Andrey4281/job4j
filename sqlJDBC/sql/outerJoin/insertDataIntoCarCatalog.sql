INSERT INTO engine(name)
VALUES
('engineOne'),
('engineTwo'),
('engineThree');

INSERT INTO carcass(name)
VALUES
('carcassOne'),
('carcassTwo'),
('carcassThree');

INSERT INTO transmission(name)
VALUES
('transmissionOne'),
('transmissionTwo'),
('transmissionThree');

INSERT INTO cars(name, id_carcass, id_engine, id_transmission)
VALUES
('carOne',(SELECT id FROM carcass WHERE carcass.name='carcassOne'),
(SELECT id FROM engine WHERE engine.name='engineOne'),
(SELECT id FROM transmission WHERE transmission.name='transmissionOne')),
('carTwo',(SELECT id FROM carcass WHERE carcass.name='carcassTwo'),
(SELECT id FROM engine WHERE engine.name='engineTwo'),
(SELECT id FROM transmission WHERE transmission.name='transmissionTwo'));