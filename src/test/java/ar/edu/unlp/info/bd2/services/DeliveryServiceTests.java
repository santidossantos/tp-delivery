package ar.edu.unlp.info.bd2.services;

import ar.edu.unlp.info.bd2.DeliveryException;
import ar.edu.unlp.info.bd2.config.AppConfig;
import ar.edu.unlp.info.bd2.config.HibernateConfiguration;
import ar.edu.unlp.info.bd2.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class, HibernateConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
@Rollback(true)
public class DeliveryServiceTests {

	@Autowired
	DeliveryService service;

	private Date dob1;
	private Date dob2;
	private Date dpri;

	@BeforeEach
	public void setUp(){
		Calendar cal1 = Calendar.getInstance();
		cal1.set(1980, Calendar.APRIL, 5);
		this.dob1 = cal1.getTime();
		cal1.set(1992, Calendar.SEPTEMBER, 16);
		this.dob2 = cal1.getTime();
		cal1.set(2022, Calendar.SEPTEMBER, 21);
		this.dpri = cal1.getTime();
	}

	@Test
	void initialTest() {
		// Probar correcta conexión con la BD y creación de entidades
	}

	@Test
	void testCreationAndGetUsers() throws DeliveryException {
		/**
		 * Creacion de Usuario tipo Cliente
		 */
		Client client = this.service.createClient("Juan Perez", "jperez", "1234", "jperez@gmail.com", dob1);
		assertNotNull(client.getId());
		assertEquals(0, client.getScore());
		assertEquals("jperez@gmail.com", client.getEmail());

		/**
		 * Creción de Usuario tipo DeliveryMan
		 */
		DeliveryMan deliveryMan = this.service.createDeliveryMan("Ramiro Benítez", "rbenitez", "1234", "rbenitez@gmail.com", dob2);
		assertNotNull(deliveryMan.getId());
		assertEquals(0, deliveryMan.getScore());
		assertEquals("rbenitez", deliveryMan.getUsername());

		/**
		 * Obtención de Usuario por ID
		 */
		Long idClient = client.getId();
		Optional<User> optionalUser1 = this.service.getUserById(idClient);
		assertTrue(optionalUser1.isPresent());
		User user1 = optionalUser1.get();
		assertEquals(Client.class, user1.getClass());
		Client client1 = (Client) user1;
		assertEquals(idClient, client1.getId());
		assertEquals("jperez", client1.getUsername());
		assertEquals("jperez@gmail.com", client1.getEmail());

		/**
		 * Obtención de Usuario por Email
		 */
		Long idDeliveryMan = deliveryMan.getId();
		Optional<User> optionalUser2 = this.service.getUserById(idDeliveryMan);
		assertTrue(optionalUser2.isPresent());
		User user2 = optionalUser2.get();
		assertEquals(user2.getClass(), DeliveryMan.class);
		DeliveryMan deliveryMan1 = (DeliveryMan) user2;
		assertEquals(idDeliveryMan, deliveryMan1.getId());
		assertEquals("rbenitez", deliveryMan1.getUsername());
		assertEquals("rbenitez@gmail.com", deliveryMan1.getEmail());

		assertFalse(this.service.getUserByEmail("otromail@gmail.com").isPresent());
	}

	@Test
	void testCreationAndGetAddresses() throws DeliveryException {
		/**
		 * Creación de Address
		 */
		Client client = this.service.createClient("Juan Perez", "jperez1", "1234", "jperez1@gmail.com", dob1);
		Address address1 = this.service.createAddress("Direccion 1", "Calle 50 y 120", 23.595f, 65.854f, "Direccion Facultad", client);
		assertNotNull(address1.getId());
		assertEquals("Direccion 1", address1.getName());
		Address address2 = this.service.createAddress("Direccion 2", "Calle 50 n5000", "12D", 24.845f, 65.084f, "Direccion Casa", client);
		assertNotNull(address2.getId());
		assertEquals("Calle 50 n5000", address2.getAddress());
		assertTrue(client.getAddresses().contains(address1));
		assertTrue(client.getAddresses().contains(address2));
	}

	@Test
	void testCreationAndGetSuppliers() throws DeliveryException {
		/**
		 * Creacion de Suppliers
		 */
		Supplier supplier = this.service.createSupplier("McDonalds", "30554442220", "Calle 50 esq 8", 24.894f, 62.489f);
		assertNotNull(supplier.getId());
		assertEquals("McDonalds", supplier.getName());

		/**
		 * Obtener Supplier por su nombre
		 */
		List<Supplier> suppliers = this.service.getSupplierByName("McDonalds");
		assertEquals(1, suppliers.size());
		List<Supplier> suppliers2 = this.service.getSupplierByName("Mc");
		assertEquals(1, suppliers2.size());

		assertEquals(0, this.service.getSupplierByName("NO EXISTE").size());
		assertThrows(DeliveryException.class, () -> this.service.createSupplier("McDonalds", "30554442220", "Calle 50 esq 8", 24.894f, 62.489f), "Constraint Violation");
	}

	@Test
	void testCreationAndGetProducts() throws DeliveryException {
		/**
		 * Creación de ProductTypes
		 */
		ProductType productType1 = this.service.createProductType("Kiosco", "Productos de kiosco, como golosinas, alfajores, etc.");
		assertNotNull(productType1.getId());
		assertEquals("Kiosco", productType1.getName());

		/**
		 * Creación de Products (con y sin fecha de precio)
		 */
		Supplier supplier = this.service.createSupplier("Kiosco 1", "30801112220", "Calle 51 esq 10", -34.917995f, -57.952061f);
		ProductType productType2 = this.service.createProductType("Helado", "Productos Helados");

		Product product1 = this.service.createProduct("Alfajor Chocolate", 180f, 80f, "Alfajor Triple de Chocolate", supplier, new ArrayList<ProductType>(Arrays.asList(productType1)));
		assertNotNull(product1.getId());
		assertEquals("Alfajor Chocolate", product1.getName());
		assertEquals(180f, product1.getPrice());
		assertEquals(1, product1.getTypes().size());
		Product product2 = this.service.createProduct("Helado Frutilla", 140f, dpri, 80f, "Helado de Agua sabor Frutilla", supplier, new ArrayList<>(Arrays.asList(productType1,productType2)));
		assertNotNull(product2.getId());
		assertEquals(2, product2.getTypes().size());

		/**
		 * Obtener producto por su ID
		 */
		Long idProduct1 = product1.getId();
		Optional<Product> optionalProduct3 = this.service.getProductById(idProduct1);
		assertTrue(optionalProduct3.isPresent());
		Product product3 = optionalProduct3.get();
		assertEquals(idProduct1, product3.getId());
		assertEquals(product1.getName(), product3.getName());

		/**
		 * Obtener producto por su nombre
		 */
		List<Product> listProduct4 = this.service.getProductByName("Helado");
		assertEquals(1, listProduct4.size());
		Product product4 = listProduct4.get(0);
		assertEquals(product2.getId(), product4.getId());
		assertEquals(product2.getName(), product4.getName());
		List<Product> listProduct5 = this.service.getProductByName("la");
		assertEquals(2, listProduct5.size());
		List<Product> listProduct6 = this.service.getProductByName("OTRO");
		assertEquals(0, listProduct6.size());

		/**
		 * Obtener productos por su tipo
		 */
		List<Product> products1 = this.service.getProductsByType("Kiosco");
		assertEquals(2, products1.size());
		List<Product> products2 = this.service.getProductsByType("Helado");
		assertEquals(1, products2.size());
		assertThrows(DeliveryException.class, () -> this.service.getProductsByType("Limpieza"), "No existe el tipo de producto");
	}

	@Test
	void testUpdatePriceProduct() throws DeliveryException {
		/**
		 * Actualizar el precio de un producto
		 */
		ProductType productType1 = this.service.createProductType("Kiosco", "Productos de kiosco, como golosinas, alfajores, etc.");
		Supplier supplier1 = this.service.createSupplier("Kiosco 1", "30801112221", "Calle 51 esq 10", -34.917995f, -57.952061f);
		Product product1 = this.service.createProduct("Alfajor Chocolate", 180f, dpri, 80f, "Alfajor Triple de Chocolate", supplier1, new ArrayList<ProductType>(Arrays.asList(productType1)));

		this.service.updateProductPrice(product1.getId(), 200);
		Product updatedProduct = this.service.getProductById(product1.getId()).orElse(null);
		assertNotNull(updatedProduct);
		assertEquals(product1.getId(), updatedProduct.getId());
		assertEquals(200, updatedProduct.getPrice());

		assertThrows(DeliveryException.class, () -> this.service.updateProductPrice(new Long(999), 200f), "No existe el producto a actualizar");
	}

	@Test
	void testCreationAndGetOrders() throws DeliveryException {
		/**
		 * Creación de Ordenes
		 */
		Client client = this.service.createClient("Juan Perez", "jperez2", "1234", "jperez2@gmail.com", dob1);
		Address address1 = this.service.createAddress("Direccion 1", "Calle 50 y 120", 23.595f, 65.854f, "Direccion Facultad", client);

		Calendar cal2 = Calendar.getInstance();
		Order order = this.service.createOrder(10, cal2.getTime(), "Una orden de prueba", client, address1);
		assertNotNull(order.getId());
		assertEquals(10, order.getNumber());
		assertEquals(0, order.getTotalPrice());

		/**
		 * Obtener orden por su id
		 */
		Long idOrder = order.getId();
		Optional<Order> optionalOrder1 = this.service.getOrderById(idOrder);
		assertTrue(optionalOrder1.isPresent());
		Order order1 = optionalOrder1.get();
		assertEquals(idOrder, order1.getId());
		assertEquals(10, order1.getNumber());

		/**
		 * Agregar Items a la orden
		 */
		ProductType productType1 = this.service.createProductType("Kiosco", "Productos de kiosco, como golosinas, alfajores, etc.");
		Supplier supplier1 = this.service.createSupplier("Kiosco 2", "30801112222", "Calle 51 esq 10", -34.917995f, -57.952061f);
		Product product1 = this.service.createProduct("Alfajor Dulce de Leche", 180f, 80f, "Alfajor Triple de Dulce de Leche", supplier1, new ArrayList<ProductType>(Arrays.asList(productType1)));

		Item item = this.service.addItemToOrder(idOrder, product1, 3, "Una descripción");
		assertNotNull(item.getId());
		assertNotNull(item.getOrder().getId());
		Order updatedOrder1 = this.service.getOrderById(idOrder).orElse(null);
		assertEquals(10, item.getOrder().getNumber());
		assertEquals(1, updatedOrder1.getItems().size());
		assertEquals(180 * 3, updatedOrder1.getTotalPrice());

		Product product2 = this.service.createProduct("Alfajor Blanco", 100f, 80f, "Alfajor Simple Blanco", supplier1, new ArrayList<ProductType>(Arrays.asList(productType1)));
		Item item2 = this.service.addItemToOrder(idOrder, product2, 1, "Una descipción");
		Order updatedOrder2 = this.service.getOrderById(idOrder).orElse(null);
		assertEquals(2, updatedOrder2.getItems().size());
		assertEquals((180 * 3) + 100, updatedOrder2.getTotalPrice());

		assertThrows(DeliveryException.class, () -> this.service.createOrder(10, cal2.getTime(), "Otra orden", client, address1 ), "Constraint Violation"); // No deberia repetirse el numero de orden
	}

	@Test
	void testCompleteOrder() throws DeliveryException {
		/**
		 * Obtener un repartidor libre aleatorio
		 */
		DeliveryMan deliveryMan1 = this.service.createDeliveryMan("Ramiro Benítez", "rbenitez3", "1234", "rbenitez3@gmail.com", dob1);
		deliveryMan1.setFree(false);
		this.service.updateDeliveryMan(deliveryMan1);
		Optional<DeliveryMan> dmEmpty = this.service.getAFreeDeliveryMan();
		assertFalse(dmEmpty.isPresent());
		DeliveryMan deliveryMan2 = this.service.createDeliveryMan("Juan Perez", "jperez3", "1234", "jperez3@gmail.com", dob2);
		Optional<DeliveryMan> dmPresent = this.service.getAFreeDeliveryMan();
		assertTrue(dmPresent.isPresent());
		DeliveryMan deliveryMan = dmPresent.get();
		assertEquals(deliveryMan2.getId(), deliveryMan.getId());

		/**
		 * Asignar un repartidor a una orden
		 */
		Client client = this.service.createClient("Rodrigo Garcia", "rgarcia", "1234", "rgarcia@gmail.com", dob1);
		Address address1 = this.service.createAddress("Direccion 1", "Calle 50 y 120", 23.595f, 65.854f, "Direccion Facultad", client);
		ProductType productType1 = this.service.createProductType("Kiosco", "Productos de kiosco, como golosinas, alfajores, etc.");
		Supplier supplier1 = this.service.createSupplier("Kiosco 3", "30801112223", "Calle 51 esq 10", -34.917995f, -57.952061f);
		Product product1 = this.service.createProduct("Alfajor de Nuez", 180f, 80f, "Alfajor simple con nueces", supplier1, new ArrayList<ProductType>(Arrays.asList(productType1)));

		Calendar cal3 = Calendar.getInstance();
		Order order = this.service.createOrder(20, cal3.getTime(), "Una orden de prueba", client, address1);
		assertFalse(this.service.addDeliveryManToOrder(order.getId(), deliveryMan)); // La orden no tiene items.
		Item item = this.service.addItemToOrder(order.getId(), product1, 3, "");
		assertFalse(this.service.addDeliveryManToOrder(order.getId(), deliveryMan1)); // El DM no esta free.
		assertThrows(DeliveryException.class, () -> this.service.addDeliveryManToOrder(new Long(50), deliveryMan), "No existe la orden");
		assertTrue(this.service.addDeliveryManToOrder(order.getId(), deliveryMan));
		DeliveryMan updatedDeliveryMan = (DeliveryMan) this.service.getUserById(deliveryMan.getId()).orElse(null);
		assertFalse(updatedDeliveryMan.isFree());
		Order updatedOrder = this.service.getOrderById(order.getId()).orElse(null);
		assertFalse(updatedOrder.isDelivered());

		/**
		 * Finalizar una orden
		 */
		Order order2 = this.service.createOrder(30, cal3.getTime(), "Otra orden", client, address1);
		assertFalse(this.service.setOrderAsDelivered(order2.getId())); //No se puede finalizar una orden no asignada
		Long idDM = updatedOrder.getDeliveryMan().getId();
		assertTrue(this.service.setOrderAsDelivered(updatedOrder.getId()));
		DeliveryMan updatedDM = (DeliveryMan) this.service.getUserById(idDM).orElse(null);
		assertTrue(updatedDM.isFree());
		assertEquals(1, updatedDM.getScore());
		assertEquals(1, updatedDM.getNumberOfSuccessOrders());
		Order updatedOrder2 = this.service.getOrderById(updatedOrder.getId()).get();
		assertTrue(updatedOrder2.isDelivered());
		assertEquals(1, updatedOrder.getClient().getScore());
	}

	@Test
	void testQualifyOrder() throws DeliveryException {
		/**
		 * Agregar una calificación
		 */
		DeliveryMan deliveryMan1 = this.service.createDeliveryMan("Ramiro Benítez", "rbenitez4", "1234", "rbenitez4@gmail.com", dob1);
		Client client = this.service.createClient("Juan Perez", "jperez4", "1234", "jperez4@gmail.com", dob2);
		Address address1 = this.service.createAddress("Direccion 1", "Calle 50 y 120", 23.595f, 65.854f, "Direccion Facultad", client);
		ProductType productType1 = this.service.createProductType("Kiosco", "Productos de kiosco, como golosinas, alfajores, etc.");
		Supplier supplier1 = this.service.createSupplier("Kiosco 5", "30801112225", "Calle 51 esq 10", -34.917995f, -57.952061f);
		Product product1 = this.service.createProduct("Alfajor de Fruta", 180f, 80f, "Alfajor Triple relleno de fruta", supplier1, new ArrayList<ProductType>(Arrays.asList(productType1)));
		Calendar cal3 = Calendar.getInstance();
		Order order = this.service.createOrder(20, cal3.getTime(), "Ultima orden de prueba", client, address1);
		Item item = this.service.addItemToOrder(order.getId(), product1, 1, "");
		assertTrue(this.service.addDeliveryManToOrder(order.getId(), deliveryMan1));
		assertTrue(this.service.setOrderAsDelivered(order.getId()));

		assertNull(order.getQualification());
		Qualification qualification = this.service.addQualificatioToOrder(order.getId(), "Excelente servicio!");
		assertNotNull(qualification.getId());
		Order updatedOrder = this.service.getOrderById(order.getId()).get();
		assertEquals(qualification.getId(), updatedOrder.getQualification().getId());
	}

}
