CREATE TABLE carcass (
id SERIAL PRIMARY KEY,
name VARCHAR(100)
);

CREATE TABLE engine (
id SERIAL PRIMARY KEY,
name VARCHAR(100)
);

CREATE TABLE transmission (
id SERIAL PRIMARY KEY,
name VARCHAR(100)
);

CREATE TABLE cars (
id SERIAL PRIMARY KEY,
name VARCHAR(100),
id_carcass INTEGER REFERENCES carcass(id),
id_engine INTEGER REFERENCES engine(id),
id_transmission INTEGER REFERENCES transmission(id)
);