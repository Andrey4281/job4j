INSERT INTO type(name)
VALUES
('сыр'),
('молоко'),
('мороженное');

INSERT INTO product(name,type_id,expired_date, price)
VALUES
('сыр голландский',(SELECT type.id FROM type WHERE type.name='сыр'),'2019-09-21 00:00',700),
('сыр российский',(SELECT type.id FROM type WHERE type.name='сыр'),'2019-10-21 00:00',400),
('мороженное пломбир', (SELECT type.id FROM type WHERE type.name='мороженное'),'2019-09-25 00:00',50),
('шоколадное мороженное', (SELECT type.id FROM type WHERE type.name='мороженное'),'2019-10-10 00:00',70),
('молоко ирбитское', (SELECT type.id FROM type WHERE type.name='молоко'),'2019-09-10 00:00',100),
('молоко простоквашино', (SELECT type.id FROM type WHERE type.name='молоко'),'2019-10-01 00:00',120);

INSERT INTO type(name)
VALUES
('хлеб');

INSERT INTO product(name,type_id,expired_date, price)
VALUES
('батон',(SELECT type.id FROM type WHERE type.name='хлеб'),'2019-09-21 00:00',700),
('булочка',(SELECT type.id FROM type WHERE type.name='хлеб'),'2019-10-21 00:00',400),
('пряники', (SELECT type.id FROM type WHERE type.name='хлеб'),'2019-09-25 00:00',50),
('белый хлеб', (SELECT type.id FROM type WHERE type.name='хлеб'),'2019-10-10 00:00',70),
('ржаной хлеб', (SELECT type.id FROM type WHERE type.name='хлеб'),'2019-09-10 00:00',100),
('каральки', (SELECT type.id FROM type WHERE type.name='хлеб'),'2019-10-01 00:00',120),
('баранки', (SELECT type.id FROM type WHERE type.name='хлеб'),'2019-10-01 00:00',120),
('сушки', (SELECT type.id FROM type WHERE type.name='хлеб'),'2019-10-01 00:00',120),
('печенье', (SELECT type.id FROM type WHERE type.name='хлеб'),'2019-10-01 00:00',120),
('торт', (SELECT type.id FROM type WHERE type.name='хлеб'),'2019-10-01 00:00',120);