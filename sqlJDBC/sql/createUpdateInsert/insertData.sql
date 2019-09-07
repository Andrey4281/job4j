INSERT INTO RightsOfRoles (descriptionOfRight, adding, removing, commenting, updating)
VALUES ('all rights',true,true,true,true),
('client rights',true,false,true,true);

INSERT INTO Role (typeOfRole)
VALUES 
('admin'),
('customer');

INSERT INTO RightsRoleConnect (id_role_Role,id_right_RightsOfRoles)
VALUES ((SELECT id_role FROM Role WHERE typeOfRole='admin'), (SELECT id_right FROM RightsOfRoles WHERE 
descriptionOfRight='all rights'));

INSERT INTO RightsRoleConnect (id_role_Role,id_right_RightsOfRoles)
VALUES ((SELECT id_role FROM Role WHERE typeOfRole='customer'), (SELECT id_right FROM RightsOfRoles WHERE 
descriptionOfRight='client rights'));

INSERT INTO Users (firstName, lastName, email, login, password, id_role_Role)
VALUES ('Andrey', 'Semenov', 'Andrey4281@yandex.ru', 'Andrey4281', '123',
(SELECT id_role FROM Role WHERE Role.typeOfRole = 'admin'));

INSERT INTO Users (firstName, lastName, email, login, password, id_role_Role)
VALUES ('Vasya', 'Pupkin', 'Pupkin4281@yandex.ru', 'Pupkin4281', 'password',
(SELECT id_role FROM Role WHERE Role.typeOfRole = 'customer'));

INSERT INTO Category(nameOfCategory)
VALUES('Books about computer science');

INSERT INTO State(stateName)
VALUES
('ready'),
('delivering');

INSERT INTO Orders (descriptionOfOrder, priceOfOrder, dateOfOrder, id_user_Users, id_state_State,
id_category_Category)
VALUES('Git for proffesional programer',857.2,'2019.09.7 10:33',
(SELECT id_user FROM Users WHERE login = 'Pupkin4281'),
(SELECT id_state FROM State WHERE stateName='ready'),
(SELECT id_category FROM Category WHERE nameOfCategory='Books about computer science')
);

INSERT INTO Orders (descriptionOfOrder, priceOfOrder, dateOfOrder, id_user_Users, id_state_State,
id_category_Category)
VALUES('Effective java',1000,'2019.09.7 10:40',
(SELECT id_user FROM Users WHERE login = 'Pupkin4281'),
(SELECT id_state FROM State WHERE stateName='delivering'),
(SELECT id_category FROM Category WHERE nameOfCategory='Books about computer science')
);

INSERT INTO Orders (id_user_Users)
VALUES(
(SELECT id_user FROM Users WHERE login = 'Andrey4281')
);

INSERT INTO Comments (contentOfComment, id_order_Orders, dateOfComment)
VALUES('This is very helpful book',
(SELECT id_order FROM Orders INNER JOIN Users ON Orders.id_user_Users = Users.id_user
WHERE Users.login='Pupkin4281' AND Orders.descriptionOfOrder='Git for proffesional programer'),
'2019.09.7 10:52');

INSERT INTO Comments (contentOfComment, id_order_Orders, dateOfComment)
VALUES('I am waiting my book',
(SELECT id_order FROM Orders INNER JOIN Users ON Orders.id_user_Users = Users.id_user
WHERE Users.login='Pupkin4281' AND Orders.descriptionOfOrder='Effective java'),
'2019.09.7 10:57');

INSERT INTO AttachedFiles (fileName, id_order_Orders)
VALUES('picture1.png',
(SELECT id_order FROM Orders INNER JOIN Users ON Orders.id_user_Users = Users.id_user
WHERE Users.login='Pupkin4281' AND Orders.descriptionOfOrder='Effective java'));

INSERT INTO AttachedFiles (fileName, id_order_Orders)
VALUES('picture2.png',
(SELECT id_order FROM Orders INNER JOIN Users ON Orders.id_user_Users = Users.id_user
WHERE Users.login='Pupkin4281' AND Orders.descriptionOfOrder='Effective java'));

INSERT INTO AttachedFiles (fileName, id_order_Orders)
VALUES('picture2.png',
(SELECT id_order FROM Orders INNER JOIN Users ON Orders.id_user_Users = Users.id_user
WHERE Users.login='Pupkin4281' AND Orders.descriptionOfOrder='Git for proffesional programer'));
