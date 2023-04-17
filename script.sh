#!/bin/bash

dbname='bd2_2023_grupo6'

echo "Ingresa tu contraseña de usuario root de MySQL: ";
mysql -u root -p  -e "CREATE DATABASE IF NOT EXISTS ${dbname};"
echo "Creando base de datos: '${dbname}'"
echo "Base de datos: '${dbname}' creada correctamente"
mysql -u root -e "CREATE USER IF NOT EXISTS 'user'@'localhost' IDENTIFIED BY 'password';"
echo "Se creo el usuario: 'user' con password: 'password'"
mysql -u root -e "GRANT ALL PRIVILEGES ON ${dbname}.* TO 'user'@'localhost';"
mysql -u root -e "FLUSH PRIVILEGES;"
mysql -u root -e "ALTER USER 'user'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';"
echo "OK"
