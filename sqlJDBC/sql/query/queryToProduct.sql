--1
SELECT product.id,product.name,product.expired_date,product.price FROM product
INNER JOIN type ON product.type_id = type.id
WHERE type.name='сыр';

--2
SELECT product.id,product.name,product.expired_date,product.price FROM product
WHERE product.name like '%мороженное%';

--3
SELECT name, expired_date FROM product
WHERE (date_trunc('month', CURRENT_DATE + INTERVAL '1 month'), date_trunc('month', CURRENT_DATE + INTERVAL '2 month'))
OVERLAPS (expired_date, expired_date);

--4
SELECT product.id,product.name,product.expired_date,product.price FROM product
WHERE product.price = (SELECT MAX(price) FROM product);

--5
SELECT type.name, COUNT(product.id)
FROM type INNER JOIN product ON type.id=product.type_id
GROUP BY (type.name);

SELECT COUNT(product.id)
FROM product INNER JOIN type ON product.type_id=type.id
WHERE type.name='сыр';

--6
SELECT product.id,product.name,product.expired_date,product.price FROM product
INNER JOIN type ON product.type_id=type.id
WHERE type.name='сыр' OR type.name='молоко';

--7
SELECT type.name, COUNT(type.name) AS count
FROM type INNER JOIN product ON type.id=product.type_id
GROUP BY type.name
HAVING COUNT(type.name)<10;

--8
SELECT product.id,product.name,product.expired_date,product.price,type.name FROM product
INNER JOIN type ON product.type_id=type.id;