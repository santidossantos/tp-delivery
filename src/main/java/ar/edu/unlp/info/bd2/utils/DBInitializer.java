package ar.edu.unlp.info.bd2.utils;

import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class DBInitializer {

    @Autowired
    DeliveryService service;

    @Transactional
    public void prepareDB() throws Exception {
        Calendar cal1 = Calendar.getInstance();
        cal1.set(1980, Calendar.APRIL, 5);
        Date dob1 = cal1.getTime();
        cal1.set(2022, Calendar.SEPTEMBER, 21);
        Date doa1 = cal1.getTime();
        LocalDate today = LocalDate.now();

        /**
         * Creación de Clientes
         */
        Client client1 = this.service.createClient("Juan Pérez", "juanperez", "1234", "juanperez@example.com", dob1);
        Client client2 = this.service.createClient("María García", "mariagarcia", "1234", "mariagarcia@example.com", dob1);
        Client client3 = this.service.createClient("Pedro López", "pedrolopez", "1234", "pedrolopez@example.com", dob1);
        Client client4 = this.service.createClient("Ana Martínez", "anamartinez", "1234", "anamartinez@example.com", dob1);
        Client client5 = this.service.createClient("Ariel Ortega", "burrito", "1234", "burritoortega@example.com", dob1);
        Client client6 = this.service.createClient("Lucía Rodríguez", "luciarodriguez", "1234", "luciarodriguez@example.com", dob1);
        Client client7 = this.service.createClient("Carlos Sánchez", "carlossanchez", "1234", "carlossanchez@example.com", dob1);
        Client client8 = this.service.createClient("Laura Fernández", "laurafernandez", "1234", "laurafernandez@example.com", dob1);
        Client client9 = this.service.createClient("Miguel Gómez", "miguelgomez", "1234", "miguelgomez@example.com", dob1);
        Client client10 = this.service.createClient("Isabel Castro", "isabelcastro", "1234", "isabelcastro@example.com", dob1);

        client1.setScore(10);
        client2.setScore(20);
        client3.setScore(30);
        client4.setScore(40);
        client5.setScore(50);
        client6.setScore(60);
        client7.setScore(70);
        client8.setScore(80);
        client9.setScore(92);
        client10.setScore(100);
        this.service.updateUser(client1);
        this.service.updateUser(client2);
        this.service.updateUser(client3);
        this.service.updateUser(client4);
        this.service.updateUser(client5);
        this.service.updateUser(client6);
        this.service.updateUser(client7);
        this.service.updateUser(client8);
        this.service.updateUser(client9);
        this.service.updateUser(client10);

        /**
         * Creación de Repartidores
         */
        DeliveryMan deliveryMan1 = this.service.createDeliveryMan("Alejandro Sánchez", "alejandrosanchez", "1234", "alejandrosanchez@example.com", dob1);
        DeliveryMan deliveryMan2 = this.service.createDeliveryMan("Carla Martínez", "carlamartinez", "1234", "carlamartinez@example.com", dob1);
        DeliveryMan deliveryMan3 = this.service.createDeliveryMan("Federico Rodríguez", "federicorodriguez", "1234", "federicorodriguez@example.com", dob1);
        DeliveryMan deliveryMan4 = this.service.createDeliveryMan("Sofía Gómez", "sofiagomez", "1234", "sofiagomez@example.com", dob1);
        DeliveryMan deliveryMan5 = this.service.createDeliveryMan("Eduardo López", "eduardolopez", "1234", "eduardolopez@example.com", dob1);
        DeliveryMan deliveryMan6 = this.service.createDeliveryMan("Marina Pérez", "marinaperez", "1234", "marinaperez@example.com", dob1);
        DeliveryMan deliveryMan7 = this.service.createDeliveryMan("Luisa García", "luisagarcia", "1234", "luisagarcia@example.com", dob1);
        DeliveryMan deliveryMan8 = this.service.createDeliveryMan("Javier Martín", "javiermartin", "1234", "javiermartin@example.com", dob1);
        DeliveryMan deliveryMan9 = this.service.createDeliveryMan("Ana Romero", "anaromero", "1234", "anaromero@example.com", dob1);
        DeliveryMan deliveryMan10 = this.service.createDeliveryMan("Daniel Sánchez", "danielsanchez", "1234", "danielsanchez@example.com", dob1);
        DeliveryMan deliveryMan11 = this.service.createDeliveryMan("Carlos Perez", "carlitos", "1234", "carlos@email.com", dob1);
        DeliveryMan deliveryMan12 = this.service.createDeliveryMan("Marta Gonzalez", "martuchi", "1234", "marta@email.com", dob1);
        DeliveryMan deliveryMan13 = this.service.createDeliveryMan("Lucas Rodriguez", "lucasito", "1234", "lucas@email.com", dob1);
        DeliveryMan deliveryMan14 = this.service.createDeliveryMan("Florencia Fernandez", "florchi", "1234", "florencia@email.com", dob1);
        DeliveryMan deliveryMan15 = this.service.createDeliveryMan("Diego Martinez", "dieguito", "1234", "diego@email.com", dob1);
        DeliveryMan deliveryMan16 = this.service.createDeliveryMan("Ana Ramirez", "anita", "1234", "ana@email.com", dob1);
        DeliveryMan deliveryMan17 = this.service.createDeliveryMan("Jorge Gutierrez", "jorgito", "1234", "jorge@email.com", dob1);
        DeliveryMan deliveryMan18 = this.service.createDeliveryMan("Sofía Lopez", "sofi", "1234", "sofia@email.com", dob1);
        DeliveryMan deliveryMan19 = this.service.createDeliveryMan("Juan Hernandez", "juanito", "1234", "juan@email.com", dob1);
        DeliveryMan deliveryMan20 = this.service.createDeliveryMan("Mauro Bosselli", "maurito", "1234", "mauroboselli@email.com", dob1);
        deliveryMan1.setScore(10);
        deliveryMan2.setScore(20);
        deliveryMan3.setScore(30);
        deliveryMan4.setScore(40);
        deliveryMan5.setScore(50);
        deliveryMan6.setScore(60);
        deliveryMan7.setScore(70);
        deliveryMan8.setScore(80);
        deliveryMan9.setScore(90);
        deliveryMan10.setScore(100);
        deliveryMan11.setScore(10);
        deliveryMan12.setScore(20);
        deliveryMan13.setScore(30);
        deliveryMan14.setScore(35);
        deliveryMan15.setScore(40);
        deliveryMan16.setScore(55);
        deliveryMan17.setScore(56);
        deliveryMan18.setScore(57);
        deliveryMan19.setScore(58);
        deliveryMan20.setScore(59);

        deliveryMan1.setNumberOfSuccessOrders(10);
        deliveryMan2.setNumberOfSuccessOrders(20);
        deliveryMan3.setNumberOfSuccessOrders(30);
        deliveryMan4.setNumberOfSuccessOrders(40);
        deliveryMan5.setNumberOfSuccessOrders(50);
        deliveryMan6.setNumberOfSuccessOrders(60);
        deliveryMan7.setNumberOfSuccessOrders(70);
        deliveryMan8.setNumberOfSuccessOrders(80);
        deliveryMan9.setNumberOfSuccessOrders(90);
        deliveryMan10.setNumberOfSuccessOrders(100);
        deliveryMan11.setNumberOfSuccessOrders(100);
        deliveryMan12.setNumberOfSuccessOrders(20);
        deliveryMan13.setNumberOfSuccessOrders(20);
        deliveryMan14.setNumberOfSuccessOrders(20);
        deliveryMan15.setNumberOfSuccessOrders(30);
        deliveryMan16.setNumberOfSuccessOrders(20);
        deliveryMan17.setNumberOfSuccessOrders(20);
        deliveryMan18.setNumberOfSuccessOrders(90);
        deliveryMan19.setNumberOfSuccessOrders(80);
        deliveryMan20.setNumberOfSuccessOrders(100);
        this.service.updateDeliveryMan(deliveryMan1);
        this.service.updateDeliveryMan(deliveryMan2);
        this.service.updateDeliveryMan(deliveryMan3);
        this.service.updateDeliveryMan(deliveryMan4);
        this.service.updateDeliveryMan(deliveryMan5);
        this.service.updateDeliveryMan(deliveryMan6);
        this.service.updateDeliveryMan(deliveryMan7);
        this.service.updateDeliveryMan(deliveryMan8);
        this.service.updateDeliveryMan(deliveryMan9);
        this.service.updateDeliveryMan(deliveryMan10);
        this.service.updateDeliveryMan(deliveryMan11);
        this.service.updateDeliveryMan(deliveryMan12);
        this.service.updateDeliveryMan(deliveryMan13);
        this.service.updateDeliveryMan(deliveryMan14);
        this.service.updateDeliveryMan(deliveryMan15);
        this.service.updateDeliveryMan(deliveryMan16);
        this.service.updateDeliveryMan(deliveryMan17);
        this.service.updateDeliveryMan(deliveryMan18);
        this.service.updateDeliveryMan(deliveryMan19);
        this.service.updateDeliveryMan(deliveryMan20);

        /**
         * Creación de Suppliers
         */
        Supplier supplier1 = this.service.createSupplier("Supplier 1", "12345678901", "Address 1", 1.0f, 2.0f);
        Supplier supplier2 = this.service.createSupplier("Supplier 2", "12345678902", "Address 2", 3.0f, 4.0f);
        Supplier supplier3 = this.service.createSupplier("Supplier 3", "12345678903", "Address 3", 5.0f, 6.0f);
        Supplier supplier4 = this.service.createSupplier("Supplier 4", "12345678904", "Address 4", 7.0f, 8.0f);
        Supplier supplier5 = this.service.createSupplier("Supplier 5", "12345678905", "Address 5", 9.0f, 10.0f);
        Supplier supplier6 = this.service.createSupplier("Supplier 6", "12345678906", "Address 6", 11.0f, 12.0f);
        Supplier supplier7 = this.service.createSupplier("Supplier 7", "12345678907", "Address 7", 13.0f, 14.0f);
        Supplier supplier8 = this.service.createSupplier("Supplier 8", "12345678908", "Address 8", 15.0f, 16.0f);
        Supplier supplier9 = this.service.createSupplier("Supplier 9", "12345678909", "Address 9", 17.0f, 18.0f);
        Supplier supplier10 = this.service.createSupplier("Supplier 10", "12345678910", "Address 10", 19.0f, 20.0f);
        Supplier supplier11 = this.service.createSupplier("Supplier 11", "12345678911", "Address 11", 20.0f, 21.0f);

        /**
         * Creación de Products Types
         */
        ProductType pType1 = this.service.createProductType("Entradas", "Platos pequeños para abrir el apetito");
        ProductType pType2 = this.service.createProductType("Platos fuertes", "Platos principales con proteínas y carbohidratos");
        ProductType pType3 = this.service.createProductType("Postres", "Dulces para cerrar la comida");
        ProductType pType4 = this.service.createProductType("Bebidas", "Refrescos, cervezas, vinos, etc.");
        ProductType pType5 = this.service.createProductType("Sopas", "Platos líquidos o cremosos");
        ProductType pType6 = this.service.createProductType("Ensaladas", "Platos fríos y frescos");
        ProductType pType7 = this.service.createProductType("Sandwiches", "Platos con pan y diferentes tipos de relleno");
        ProductType pType8 = this.service.createProductType("Pastas", "Platos con diferentes tipos de pasta y salsas");
        ProductType pType9 = this.service.createProductType("Pizzas", "Platos con base de pizza y diferentes tipos de ingredientes");
        ProductType pType10 = this.service.createProductType("Desayunos", "Platos para comenzar el día");


        /**
         * Creacion de products
         */
        Product product1 = this.service.createProduct("Hamburguesa", 10.50f, java.sql.Date.valueOf(today.minusDays(1)), 200f, "Hamburguesa con queso y tocino", supplier1, new ArrayList<>(Arrays.asList(pType2, pType1, pType7)));
        Product product2 = this.service.createProduct("Pizza Margarita", 8.75f, java.sql.Date.valueOf(today.minusDays(2)),300f, "Pizza con salsa de tomate y queso mozzarella", supplier2, new ArrayList<>(Arrays.asList(pType9)));
        Product product3 = this.service.createProduct("Ensalada Caesar", 7.25f, java.sql.Date.valueOf(today.minusDays(3)),150f, "Ensalada con lechuga, crutones y aderezo Caesar", supplier3, new ArrayList<>(Arrays.asList(pType6)));
        Product product4 = this.service.createProduct("Hot Dog", 4.50f, java.sql.Date.valueOf(today.minusDays(4)),100f,  "Salchicha con pan y condimentos", supplier4, new ArrayList<>(Arrays.asList(pType1)));
        Product product5 = this.service.createProduct("Sushi Roll", 12.25f,java.sql.Date.valueOf(today.minusDays(4)), 150f, "Roll de sushi con salmón y aguacate", supplier5, new ArrayList<>(Arrays.asList(pType2)));
        Product product6 = this.service.createProduct("Spaghetti Bolognese", 9.99f, java.sql.Date.valueOf(today.minusDays(25)),250f, "Spaghetti con salsa bolognesa de carne molida", supplier6, new ArrayList<>(Arrays.asList(pType8)));
        Product product7 = this.service.createProduct("Pollo Asado", 12.75f, java.sql.Date.valueOf(today.minusDays(15)),350f, "Pollo asado con papas fritas y ensalada", supplier7, new ArrayList<>(Arrays.asList(pType2)));
        Product product8 = this.service.createProduct("Helado de Vainilla", 3.50f, java.sql.Date.valueOf(today.minusDays(12)),100f, "Helado de vainilla con toppings de tu elección", supplier8, new ArrayList<>(Arrays.asList(pType3)));
        Product product9 = this.service.createProduct("Taco de Carne Asada", 2.99f, java.sql.Date.valueOf(today.minusDays(10)),150f, "Taco de carne asada con cilantro y cebolla", supplier9, new ArrayList<>(Arrays.asList(pType1)));
        Product product10 = this.service.createProduct("Cheesecake", 5.75f, java.sql.Date.valueOf(today.minusDays(20)),120f, "Cheesecake de fresa con galleta de chocolate", supplier10, new ArrayList<>(Arrays.asList(pType3)));
        Product product11 = this.service.createProduct("Coca-Cola", 1.99f, java.sql.Date.valueOf(today.minusDays(60)),500f, "Bebida gaseosa con sabor a cola", supplier1, new ArrayList<>(Arrays.asList(pType4)));
        Product product12 = this.service.createProduct("Café Americano", 2.50f,java.sql.Date.valueOf(today.minusDays(7)), 250f, "Café negro sin leche", supplier2, new ArrayList<>(Arrays.asList(pType10)));
        Product product13 = this.service.createProduct("Té Verde", 2.25f, java.sql.Date.valueOf(today.minusDays(7)),200f, "Té verde con hojas de menta", supplier3, new ArrayList<>(Arrays.asList(pType10)));
        Product product14 = this.service.createProduct("Hamburguesa con queso", 5.5f, java.sql.Date.valueOf(today.minusDays(1)),0.3f, "Hamburguesa de carne con queso cheddar derretido", supplier2, new ArrayList<>(Arrays.asList(pType3)));
        Product product15 = this.service.createProduct("Ensalada de pollo", 4.5f, java.sql.Date.valueOf(today.minusDays(1)),0.25f, "Ensalada de lechuga, pollo a la parrilla y croutones", supplier4, new ArrayList<>(Arrays.asList(pType4)));
        Product product16 = this.service.createProduct("Papas fritas", 3.0f, java.sql.Date.valueOf(today.minusDays(5)),0.2f, "Papas fritas crujientes y calientes", supplier2, new ArrayList<>(Arrays.asList(pType3)));
        Product product17 = this.service.createProduct("Pizza 4 Quesos", 8.0f, java.sql.Date.valueOf(today.minusDays(3)),0.5f, "Pizza de 4 quesos diferentes", supplier6, new ArrayList<>(Arrays.asList(pType3, pType9)));
        Product product18 = this.service.createProduct("Agua mineral", 1.5f, java.sql.Date.valueOf(today.minusDays(1)),0.5f, "Botella de agua mineral sin gas", supplier5, new ArrayList<>(Arrays.asList(pType1, pType5)));
        Product product19 = this.service.createProduct("Gaseosa cola", 2.5f, java.sql.Date.valueOf(today.minusDays(1)),0.5f, "Botella de gaseosa cola", supplier5, new ArrayList<>(Arrays.asList(pType1, pType5)));
        Product product20 = this.service.createProduct("Sándwich vegetariano", 4.0f, java.sql.Date.valueOf(today.minusDays(40)),0.25f, "Pan integral con vegetales frescos", supplier4, new ArrayList<>(Arrays.asList(pType3, pType4)));
        Product product21 = this.service.createProduct("Galletitas de avena", 1.5f, java.sql.Date.valueOf(today.minusDays(7)),0.05f, "Galletitas integrales con avena", supplier7, new ArrayList<>(Arrays.asList(pType2, pType5)));
        Product product22 = this.service.createProduct("Sandwich de jamón y queso", 3.5f, java.sql.Date.valueOf(today.minusDays(7)),0.15f, "Pan de molde, jamón y queso amarillo", supplier3, new ArrayList<>(Arrays.asList(pType3)));
        Product product23 = this.service.createProduct("Ensalada César", 4.0f, java.sql.Date.valueOf(today.minusDays(11)), 0.25f, "Lechuga, pollo a la parrilla, croutones y aderezo césar", supplier4, new ArrayList<>(Arrays.asList(pType4)));
        Product product24 = this.service.createProduct("Té japones", 2.0f, java.sql.Date.valueOf(today.minusDays(1)),0.3f, "Té verde de origen japonés", supplier8, new ArrayList<>(Arrays.asList(pType1)));
        Product product25 = this.service.createProduct("Café con leche", 2.5f, java.sql.Date.valueOf(today.minusDays(10)),0.25f, "Café con leche y un poco de azúcar", supplier9, new ArrayList<>(Arrays.asList(pType1)));
        Product product26 = this.service.createProduct("Muffin de arándanos", 2.0f, java.sql.Date.valueOf(today.minusDays(10)),0.2f, "Muffin con arándanos frescos", supplier7, new ArrayList<>(Arrays.asList(pType2, pType5)));
        Product product27 = this.service.createProduct("Empanada de carne", 2.5f, java.sql.Date.valueOf(today.minusDays(1)), 0.15f, "Empanada rellena de carne picada", supplier3, new ArrayList<>(Arrays.asList(pType3)));
        Product product28 = this.service.createProduct("Ensalada de frutas", 3.0f, java.sql.Date.valueOf(today.minusDays(1)),0.3f, "Ensalada de frutas frescas con yogurt", supplier4, new ArrayList<>(Arrays.asList(pType4, pType5)));
        Product product29 = this.service.createProduct("Jugo de naranja", 2.5f, java.sql.Date.valueOf(today.minusDays(1)),0.35f, "Jugo de naranja natural", supplier5, new ArrayList<>(Arrays.asList(pType1, pType5)));
        Product product30 = this.service.createProduct("Galletitas de chocolate", 1.5f, java.sql.Date.valueOf(today.minusDays(12)),0.05f, "Galletitas de chocolate con chips de chocolate", supplier7, new ArrayList<>(Arrays.asList(pType2, pType5)));

        /**
         * Creación de Addresses
         */
        Address address1 = this.service.createAddress("Address 1", "Calle Falsa 123", 12.34f, 56.78f, "", client1);
        Address address2 = this.service.createAddress("Address 2", "Avenida Siempreviva 742", 23.45f, 67.89f, "", client2);
        Address address3 = this.service.createAddress("Address 3", "Calle del Sol 321", 34.56f, 78.90f, "", client3);
        Address address4 = this.service.createAddress("Address 4", "Avenida de Mayo 745", 45.67f, 89.01f, "", client4);
        Address address5 = this.service.createAddress("Address 5", "Calle Corrientes 1765", 56.78f, 90.12f, "", client5);
        Address address6 = this.service.createAddress("Address 6", "Avenida Santa Fe 2867", 67.89f, 1.23f, "", client6);
        Address address7 = this.service.createAddress("Address 7", "Calle Lavalle 1134", 78.90f, 2.34f, "", client7);
        Address address8 = this.service.createAddress("Address 8", "Avenida Libertador 5432", 89.01f, 3.45f, "", client8);
        Address address9 = this.service.createAddress("Address 9", "Calle Florida 666", 90.12f, 4.56f, "", client9);
        Address address10 = this.service.createAddress("Address 10", "Avenida 9 de Julio 1024", 1.23f, 5.67f, "", client10);

        /**
         * Creación de Ordenes con Items
         */

        Order order1 = this.service.createOrder(1, java.sql.Date.valueOf(today.minusDays(1)), "", client1, address1);
        Item item1 = this.service.addItemToOrder(order1.getId(), product1, 2, "");
        Item item2 = this.service.addItemToOrder(order1.getId(), product2, 1, "");

        Order order2 = this.service.createOrder(2, java.sql.Date.valueOf(today), "", client1, address1);
        Item item3 = this.service.addItemToOrder(order2.getId(), product3, 3, "");
        Item item4 = this.service.addItemToOrder(order2.getId(), product4, 2, "");
        Item item5 = this.service.addItemToOrder(order2.getId(), product5, 1, "");

        Order order3 = this.service.createOrder(3, java.sql.Date.valueOf(today), "", client1, address1);
        Item item6 = this.service.addItemToOrder(order3.getId(), product6, 4, "");

        Order order4 = this.service.createOrder(4, java.sql.Date.valueOf(today.minusDays(10)), "", client2, address2);
        Item item7 = this.service.addItemToOrder(order4.getId(), product7, 1, "");
        Item item8 = this.service.addItemToOrder(order4.getId(), product8, 1, "");

        Order order5 = this.service.createOrder(5, java.sql.Date.valueOf(today.minusDays(5)), "", client2, address2);
        Item item9 = this.service.addItemToOrder(order5.getId(), product9, 3, "");
        Item item10 = this.service.addItemToOrder(order5.getId(), product10, 2, "");

        Order order6 = this.service.createOrder(6, java.sql.Date.valueOf(today.minusDays(6)), "", client2, address2);
        Item item11 = this.service.addItemToOrder(order6.getId(), product1, 5, "");

        Order order7 = this.service.createOrder(7, java.sql.Date.valueOf(today.minusDays(25)), "", client3, address3);
        Item item12 = this.service.addItemToOrder(order7.getId(), product2, 2, "");
        Item item13 = this.service.addItemToOrder(order7.getId(), product3, 1, "");

        Order order8 = this.service.createOrder(8, java.sql.Date.valueOf(today.minusDays(3)), "", client3, address3);
        Item item14 = this.service.addItemToOrder(order8.getId(), product4, 3, "");
        Item item15 = this.service.addItemToOrder(order8.getId(), product5, 2, "");
        Item item16 = this.service.addItemToOrder(order8.getId(), product6, 2, "");

        Order order9 = this.service.createOrder(9, java.sql.Date.valueOf(today.minusDays(10)), "", client3, address3);
        Item item17 = this.service.addItemToOrder(order9.getId(), product7, 4, "");

        Order order10 = this.service.createOrder(10, java.sql.Date.valueOf(today.minusDays(9)), "", client4, address4);
        Item item18 = this.service.addItemToOrder(order10.getId(), product8, 2, "");
        Item item19 = this.service.addItemToOrder(order10.getId(), product9, 1, "");

        Order order11 = this.service.createOrder(11, java.sql.Date.valueOf(today.minusDays(1)), "", client4, address4);
        Item item20 = this.service.addItemToOrder(order11.getId(), product10, 2, "");

        Order order12 = this.service.createOrder(12, java.sql.Date.valueOf(today), "", client2, address2);
        Item item12_1 = this.service.addItemToOrder(order12.getId(), product3, 3, "");
        Item item12_2 = this.service.addItemToOrder(order12.getId(), product4, 1, "");

        Order order13 = this.service.createOrder(13, java.sql.Date.valueOf(today), "", client3, address3);
        Item item13_1 = this.service.addItemToOrder(order13.getId(), product5, 1, "");
        Item item13_2 = this.service.addItemToOrder(order13.getId(), product6, 2, "");
        Item item13_3 = this.service.addItemToOrder(order13.getId(), product7, 3, "");

        Order order14 = this.service.createOrder(14, java.sql.Date.valueOf(today), "", client4, address4);
        Item item14_1 = this.service.addItemToOrder(order14.getId(), product8, 1, "");
        Item item14_2 = this.service.addItemToOrder(order14.getId(), product9, 1, "");

        Order order15 = this.service.createOrder(15, java.sql.Date.valueOf(today.minusDays(3)), "", client5, address5);
        Item item15_1 = this.service.addItemToOrder(order15.getId(), product10, 1, "");

        Order order16 = this.service.createOrder(16, java.sql.Date.valueOf(today.minusDays(4)), "", client6, address6);
        Item item16_1 = this.service.addItemToOrder(order16.getId(), product1, 1, "");
        Item item16_2 = this.service.addItemToOrder(order16.getId(), product2, 2, "");

        Order order17 = this.service.createOrder(17, java.sql.Date.valueOf(today.minusDays(25)), "", client7, address7);
        Item item17_1 = this.service.addItemToOrder(order17.getId(), product3, 1, "");
        Item item17_2 = this.service.addItemToOrder(order17.getId(), product4, 2, "");

        Order order18 = this.service.createOrder(18, java.sql.Date.valueOf(today.minusDays(1)), "", client8, address8);
        Item item18_1 = this.service.addItemToOrder(order18.getId(), product5, 1, "");

        Order order19 = this.service.createOrder(19, java.sql.Date.valueOf(today.minusDays(2)), "", client9, address9);
        Item item19_1 = this.service.addItemToOrder(order19.getId(), product6, 3, "");
        Item item19_2 = this.service.addItemToOrder(order19.getId(), product7, 2, "");

        Order order20 = this.service.createOrder(20, java.sql.Date.valueOf(today.minusDays(1)), "", client10, address10);
        Item item20_1 = this.service.addItemToOrder(order20.getId(), product8, 1, "");
        Item item20_2 = this.service.addItemToOrder(order20.getId(), product9, 1, "");
        Item item20_3 = this.service.addItemToOrder(order20.getId(), product10, 2, "");

        Order order21 = this.service.createOrder(21, java.sql.Date.valueOf(today.minusDays(10)), "", client1, address1);
        Item item21_1 = this.service.addItemToOrder(order21.getId(), product1, 2, "");
        Item item21_2 = this.service.addItemToOrder(order21.getId(), product2, 1, "");

        Order order22 = this.service.createOrder(22, java.sql.Date.valueOf(today), "", client2, address2);
        Item item22_1 = this.service.addItemToOrder(order22.getId(), product3, 1, "");
        Item item22_2 = this.service.addItemToOrder(order22.getId(), product4, 3, "");
        Item item22_3 = this.service.addItemToOrder(order22.getId(), product5, 2, "");

        Order order23 = this.service.createOrder(23, java.sql.Date.valueOf(today), "", client3, address3);
        Item item23_1 = this.service.addItemToOrder(order23.getId(), product6, 1, "");
        Item item23_2 = this.service.addItemToOrder(order23.getId(), product28, 1, "");
        Item item23_3 = this.service.addItemToOrder(order23.getId(), product29, 1, "");

        Order order24 = this.service.createOrder(24, java.sql.Date.valueOf(today.minusDays(3)), "", client4, address4);
        Item item24_1 = this.service.addItemToOrder(order24.getId(), product7, 1, "");
        Item item24_2 = this.service.addItemToOrder(order24.getId(), product8, 2, "");

        Order order25 = this.service.createOrder(25, java.sql.Date.valueOf(today.minusDays(5)), "", client5, address5);
        Item item25_1 = this.service.addItemToOrder(order25.getId(), product9, 1, "");
        Item item25_2 = this.service.addItemToOrder(order25.getId(), product10, 3, "");

        Order order26 = this.service.createOrder(26, java.sql.Date.valueOf(today.minusDays(1)), "", client6, address6);
        Item item26_1 = this.service.addItemToOrder(order26.getId(), product11, 2, "");
        Item item26_2 = this.service.addItemToOrder(order26.getId(), product12, 1, "");

        Order order27 = this.service.createOrder(27, java.sql.Date.valueOf(today), "", client7, address7);
        Item item27_1 = this.service.addItemToOrder(order27.getId(), product13, 3, "");
        Item item27_2 = this.service.addItemToOrder(order27.getId(), product14, 2, "");
        Item item27_3 = this.service.addItemToOrder(order27.getId(), product30, 2, "");

        Order order28 = this.service.createOrder(28, java.sql.Date.valueOf(today), "", client8, address8);
        Item item28_1 = this.service.addItemToOrder(order28.getId(), product15, 1, "");
        Item item28_2 = this.service.addItemToOrder(order28.getId(), product24, 1, "");
        Item item28_3 = this.service.addItemToOrder(order28.getId(), product25, 1, "");

        Order order29 = this.service.createOrder(29, java.sql.Date.valueOf(today), "", client9, address9);
        Item item29_1 = this.service.addItemToOrder(order29.getId(), product16, 2, "");
        Item item29_2 = this.service.addItemToOrder(order29.getId(), product17, 1, "");

        Order order30 = this.service.createOrder(30, java.sql.Date.valueOf(today.minusDays(8)), "", client10, address10);
        Item item30_1 = this.service.addItemToOrder(order30.getId(), product18, 1, "");
        Item item30_2 = this.service.addItemToOrder(order30.getId(), product19, 2, "");
        Item item30_3 = this.service.addItemToOrder(order30.getId(), product20, 3, "");

        this.service.addDeliveryManToOrder(order1.getId(), deliveryMan1);
        this.service.addDeliveryManToOrder(order2.getId(), deliveryMan2);
        this.service.addDeliveryManToOrder(order3.getId(), deliveryMan3);
        this.service.addDeliveryManToOrder(order4.getId(), deliveryMan4);
        this.service.addDeliveryManToOrder(order5.getId(), deliveryMan5);
        this.service.addDeliveryManToOrder(order6.getId(), deliveryMan6);
        this.service.addDeliveryManToOrder(order7.getId(), deliveryMan7);
        this.service.addDeliveryManToOrder(order8.getId(), deliveryMan8);
        this.service.addDeliveryManToOrder(order9.getId(), deliveryMan9);
        this.service.addDeliveryManToOrder(order10.getId(), deliveryMan10);

        this.service.setOrderAsDelivered(order1.getId());
        this.service.setOrderAsDelivered(order2.getId());
        this.service.setOrderAsDelivered(order3.getId());
        this.service.setOrderAsDelivered(order4.getId());
        this.service.setOrderAsDelivered(order5.getId());
        this.service.setOrderAsDelivered(order6.getId());
        this.service.setOrderAsDelivered(order7.getId());
        this.service.setOrderAsDelivered(order8.getId());
        this.service.setOrderAsDelivered(order9.getId());
        this.service.setOrderAsDelivered(order10.getId());

        /**
         * Creación de las calificaciones
         */
        Qualification qua1 = this.service.addQualificatioToOrder(order1.getId(), "");
        Qualification qua2 = this.service.addQualificatioToOrder(order2.getId(), "");
        Qualification qua3 = this.service.addQualificatioToOrder(order3.getId(), "");
        Qualification qua4 = this.service.addQualificatioToOrder(order4.getId(), "");
        Qualification qua5 = this.service.addQualificatioToOrder(order5.getId(), "");
        Qualification qua6 = this.service.addQualificatioToOrder(order6.getId(), "");
        Qualification qua7 = this.service.addQualificatioToOrder(order7.getId(), "");
        Qualification qua8 = this.service.addQualificatioToOrder(order8.getId(), "");
        Qualification qua9 = this.service.addQualificatioToOrder(order9.getId(), "");
        Qualification qua10 = this.service.addQualificatioToOrder(order10.getId(), "");

        qua1.setScore(3);
        qua2.setScore(1);
        qua3.setScore(2);
        qua4.setScore(4);
        qua5.setScore(4);
        qua6.setScore(1);
        qua7.setScore(5);
        qua8.setScore(2);
        qua9.setScore(5);
        qua10.setScore(2);

        this.service.updateQualification(qua1);
        this.service.updateQualification(qua2);
        this.service.updateQualification(qua3);
        this.service.updateQualification(qua4);
        this.service.updateQualification(qua5);
        this.service.updateQualification(qua6);
        this.service.updateQualification(qua7);
        this.service.updateQualification(qua8);
        this.service.updateQualification(qua9);
        this.service.updateQualification(qua10);


    }

}