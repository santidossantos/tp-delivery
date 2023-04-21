#!/bin/bash

dbname='bd2_2023_grupo6'
user='usuario'

echo "Ingrese tu contraseña de usuario root de MySQL: ";
echo "Creando base de datos: '${dbname}'"
mysql -u root -p  -e "CREATE DATABASE IF NOT EXISTS ${dbname};"
mysql -u root -e "CREATE USER IF NOT EXISTS '${user}'@'localhost' IDENTIFIED BY 'password';"
mysql -u root -e "ALTER USER '${user}'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';"
echo "Se creo el usuario: '${user}' con password: 'password'"
mysql -u root -e "GRANT CREATE, ALTER, SELECT, INSERT, UPDATE ON ${dbname}.* TO '${user}'@'localhost';"
mysql -u root -e "FLUSH PRIVILEGES;"