CREATE DATABASE IF NOT EXISTS bd2_2023_grupo6;
CREATE USER IF NOT EXISTS 'usuario'@'localhost' IDENTIFIED BY 'password';
ALTER USER 'usuario'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';
GRANT ALL PRIVILEGES ON bd2_2023_grupo6.* TO 'usuario'@'localhost';
FLUSH PRIVILEGES;
