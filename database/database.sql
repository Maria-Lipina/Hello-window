-- DROP DATABASE IF EXISTS jfx; #Java FX first application datadase
-- CREATE DATABASE jfx;
-- USE jfx;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(20),
    lastname VARCHAR(20),
    email VARCHAR(50) UNIQUE,
    password_hash BIGINT
    );
    
INSERT INTO jfx.users (firstname, lastname, email)
SELECT first_name, last_name, email
FROM sakila.customer;


DROP TABLE IF EXISTS passwords;
CREATE TEMPORARY TABLE passwords (
u_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
u_pass_hash BIGINT,
u_pass VARCHAR(100)
);


LOAD DATA INFILE "passGen.txt" INTO TABLE passwords 
COLUMNS TERMINATED BY ';' LINES TERMINATED BY '\n'
(u_pass_hash, u_pass);

# Файл здесь C:\ProgramData\MySQL\MySQL Server 8.0\Data\jfx если не указан абсолютный путь к другой папке
# Стандартно скрипты здесь C:\Users\Admin\AppData\Roaming\DBeaverData\workspace6\General\Scripts


UPDATE users
SET password_hash = ( SELECT u_pass_hash FROM passwords WHERE users.id = passwords.u_id);

DELETE FROM users WHERE id > 600;

