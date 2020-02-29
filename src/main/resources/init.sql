DELETE FROM mysql.user WHERE user='influ';
DROP DATABASE influential_people;
DROP USER influ@localhost;

CREATE DATABASE influential_people;

CREATE USER 'influ'@'localhost' IDENTIFIED BY 'influ';
GRANT ALL PRIVILEGES ON influential_people.* TO 'influ'@'localhost';
