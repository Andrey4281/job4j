--1
SELECT person.name, company.name FROM person
INNER JOIN company ON person.company_id = company.id
WHERE company.id != 5;

--2
SELECT company.name, COUNT(person.id) AS countOfPerson FROM company
INNER JOIN person ON company.id = person.company_id 
GROUP BY company.name
HAVING COUNT(person.id) = (SELECT MAX(t.cou) FROM (SELECT COUNT(person.id) AS cou FROM
person INNER JOIN company ON person.company_id = company.id
GROUP BY company.id) AS t);