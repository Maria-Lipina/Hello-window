DROP DATABASE IF EXISTS jfx; #Java FX first application datadase
CREATE DATABASE jfx;
USE jfx;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(20),
    lastname VARCHAR(20),
    email VARCHAR(50) UNIQUE,
    password_hash varchar(256)
    );
    
INSERT INTO jfx.users (firstname, lastname, email)
SELECT first_name, last_name, email
from sakila.customer;