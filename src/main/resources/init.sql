CREATE DATABASE influential_people;

CREATE USER 'influ'@'localhost' IDENTIFIED BY 'influ';
GRANT ALL PRIVILEGES ON influential_people.* TO 'influ'@'localhost';
