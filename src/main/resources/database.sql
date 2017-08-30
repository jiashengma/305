-- Author: Jia Sheng Ma

-- TODO: keys, constraints
CREATE TABLE airline (
    id      int,
    name    char(2)
);

CREATE TABLE airport (
    id      int,
    name    char(3),
    city    varchar(64),
    country varchar(64)
);

-- TODO
CREATE TABLE flight (
    number  int,
    -- airline
);

CREATE TABLE reservation ();
CREATE TABLE customer();
CREATE TABLE employee();
