# Bases de Datos 2

## Trabajo Practico Integrador - Parte I

### Introducción

Esta primera etapa del Trabajo Práctico Integrador estará basada en la implementación de una aplicación Java orientada al estudio de la persistencia en una base de datos relacional, tanto en términos conceptuales, como en el uso de tecnología en particular. La aplicación consistirá en una arquitectura multicapa clásica, debiéndose implementar una capa de servicios y otra de acceso a datos subyacentes.

La cátedra entregará un proyecto modelo implementado con Maven y el framework Spring y una configuración de Hibernate inicial, así como una serie de test cases que se deben satisfacer. Cada grupo deberá implementar todo lo necesario para que estos test cases pasen. En términos concretos, al correr el comando

`mvn clean install`

desde la línea de comandos debe obtenerse un build exitoso en donde pasen todos los tests, habiendo previamente instalado la BBDD. Para esto, se debe entregar también un archivo .sql que contenga sólo los comandos MySQL para crear la BBDD y el usuario correspondiente. No se debe utilizar el usuario root para acceder a la BBDD, sino que se debe crear un nuevo usuario con permisos de acceso.

El proyecto base se encuentra disponible en GitHub donde se puede descargar e inicializar un repositorio propio para el grupo.
IMPORTANTE:
No realizar un fork del proyecto ya que este tomará la misma visibilidad de público que el proyecto base, este nuevo repositorio debe ser privado.
Redefinir el método getGroupNumber() de la clase HibernateConfiguration para que retorne su número de grupo.

### Dominio de la aplicación a implementar

Se quiere generar un sistema de pedidos online similar a Rappi o PedidosYa. El diagrama de clases de este sistema se puede ver en la figura 1.

En el sistema mencionado modela usuarios (User) que se pueden registrar para ser repartidores (DeliveryMan), o bien para ser clientes y ordenar pedidos (Client). Los usuarios se identifican mediante un mail, nombre de usuario y contraseña, y de ellos se conoce además el nombre, fecha de nacimiento y un puntaje obtenido a partir de sus pedidos. Además, de cada Cliente se conoce una lista de direcciónes (Address) que este registra para enviar sus pedidos.

Un cliente puede ordenar un pedido (Order) de un conjunto de productos que es ofrecido por uno de los proveedores de la plataforma. Cada orden contiene un número de orden (que no puede repetirse), la fecha de creación, el precio total y una descripción o comentario opcional. Cuando genera un pedido, el cliente debe elegir una de sus direcciones como lugar de entrega.

Cada orden se compone de una serie de items (Item), que representa el detalle de la orden, es decir, el producto solicitado y la cantidad del mismo. Opcionalmente el cliente puede agregar una descripción o aclaración sobre el producto ordenado.

El pedido se asigna de forma aleatoria a alguno de los repartidores que se encuentra libre, quien quedará ocupado hasta que el pedido sea finalizado. Una vez finalizado, se aumenta en 1 el score del cliente y del repartidor y el número de órdenes completadas del repartidor.

El cliente puede generar una única calificación (Qualification), solo al ser completada la entrega, en la cual podrá establecer un puntaje (de una a cinco estrellas) y una opinión en forma de texto.

El sistema también cuenta con la información de proveedores (Supplier), de los que se conoce nombre, cuit (único en el sistema), dirección y un listado de productos que ofrece. De cada producto (Product), que es exclusivo de un proveedor, se almacena su nombre, precio junto a la fecha de su ultima actualización, peso y  una descripción. Estos productos, con el objetivo de facilitar las búsquedas del usuario, son clasificados en diferentes tipos (ProductType). Un producto puede tener muchos tipos y un tipo estar asociado a varios productos.

### Requisitos detallados que deben satisfacerse en esta etapa

1. Definir el método _HibernateConfiguration.getGroupNumber()_ para que devuelva un Integer con el número de grupo. 
2. Escribir todo el código necesario para que la aplicación compile y todos los tests de la clase _DeliveryServiceTests_ pasen. Esto implica codificar una implementación concreta de la interfaz _DeliveryService_ cuyos métodos están descritos vía Javadoc en la misma. 
3. Deben proveer un script sql que inicialice la base de datos en sí y cualquier otra configuración que sea necesaria (por ejemplo, usuarios y permisos) llamado createDatabase.sql 
4. Correr el script anterior y luego mvn clean install debe resultar en un build exitoso en donde todos los tests pasen 
5. El código tiene que estar subido en un repositorio privado de GitHub y la forma de entrega será por este medio. El repositorio privado deberá compartirse con el ayudante designado para el grupo.

### Requisitos tecnicos

1. Tener instalado Java 8 o posterior (javac -version desde la línea de comandos debe funcionar)
2. Tener instalado Maven 4.0 (mvn --version desde la línea de comando debe funcionar)
3. Tener instalado MySQL 5.7 o posterior (Importante: no utilizar MariaDB)
4. Es altamente recomendable Utilizar un IDE para Java, preferentemente IntelliJ o Eclipse - Nota: algunas versiones de Eclipse tienen problemas de compatibilidad con JUnit 5, el cual se utiliza para test cases en el proyecto modelo entregado por la Cátedra. JDK 11

### Fecha de Entrega

Esta primera etapa tendrá como fecha de entrega el 15 de mayo, junto con la etapa dos del mismo.