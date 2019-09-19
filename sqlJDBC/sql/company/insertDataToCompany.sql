INSERT INTO company(name)
VALUES
('kontur'),
('naumen'),
('pro-soft'),
('yandex'),
('mail.ru');

INSERT INTO person(name, company_id)
VALUES
('Andrey', (SELECT company.id FROM company WHERE company.name = 'naumen')),
('Sergey', (SELECT company.id FROM company WHERE company.name = 'naumen')),
('Alexey', (SELECT company.id FROM company WHERE company.name = 'kontur')),
('Roman', (SELECT company.id FROM company WHERE company.name = 'pro-soft')),
('Petr', (SELECT company.id FROM company WHERE company.name = 'yandex')),
('Vitaliy', (SELECT company.id FROM company WHERE company.name = 'mail.ru'));