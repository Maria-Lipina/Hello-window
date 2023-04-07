DROP DATABASE IF EXISTS jfx; #Java FX first application datadase
CREATE DATABASE jfx;
USE jfx;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(20),
    lastname VARCHAR(20),
    email VARCHAR(50) UNIQUE,
    # password_hash BIGINT
    password_hash VARCHAR(8)
    );
    
INSERT INTO jfx.users (firstname, lastname, email)
SELECT first_name, last_name, email
FROM sakila.customer;

SELECT COUNT(id) FROM users;

DROP TABLE IF EXISTS passwords;
CREATE TABLE passwords (
u_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
u_password VARCHAR(8)
);

LOAD DATA INFILE "D:\\IT\\Java\\Sandbox\\Hello window\\database\\passGen.txt" INTO TABLE passwords (u_password);
# Файл здесь C:\ProgramData\MySQL\MySQL Server 8.0\Data\jfx если не указан абсолютный путь к другой папке
# Стандартно скрипты здесь C:\Users\Admin\AppData\Roaming\DBeaverData\workspace6\General\Scripts


UPDATE users
SET password_hash = ( SELECT u_password FROM passwords WHERE users.id = passwords.u_id);





