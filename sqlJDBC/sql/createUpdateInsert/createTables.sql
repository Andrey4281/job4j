CREATE TABLE RightsOfRoles (
id_right SERIAL PRIMARY KEY,
descriptionOfRight TEXT,
adding BOOLEAN,
removing BOOLEAN,
commenting BOOLEAN,
updating BOOLEAN
);

CREATE TABLE State (
id_state SERIAL PRIMARY KEY,
stateName VARCHAR(20)
);

CREATE TABLE Category (
id_category SERIAL PRIMARY KEY,
nameOfCategory VARCHAR(50)
);

CREATE TABLE Role (
id_role SERIAL PRIMARY KEY,
typeOfRole VARCHAR(30)
);

CREATE TABLE RightsRoleConnect (
id_r_role SERIAL PRIMARY KEY,
id_role_Role INTEGER REFERENCES Role(id_role),
id_right_RightsOfRoles INTEGER REFERENCES RightsOfRoles(id_right)
);
 

CREATE TABLE Users (
id_user SERIAL PRIMARY KEY,
firstName VARCHAR(50),
lastName VARCHAR(50),
email VARCHAR(50),
login VARCHAR(50),
password VARCHAR(30),
id_role_Role INTEGER REFERENCES Role(id_role)
);

CREATE TABLE Orders (
id_order SERIAL PRIMARY KEY,
descriptionOfOrder TEXT,
priceOfOrder DOUBLE PRECISION,
dateOfOrder TIMESTAMP,
id_user_Users INTEGER REFERENCES Users(id_user),
id_state_State INTEGER REFERENCES State(id_state),
id_category_Category INTEGER REFERENCES Category(id_category)
);

CREATE TABLE AttachedFiles (
id_file SERIAL PRIMARY KEY,
fileName VARCHAR(50),
id_order_Orders INTEGER REFERENCES Orders(id_order)
);

CREATE TABLE Comments (
id_comment SERIAL PRIMARY KEY,
contentOfComment TEXT,
dateOfComment TIMESTAMP,
id_order_Orders INTEGER REFERENCES Orders(id_order)
);
