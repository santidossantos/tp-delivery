package ar.edu.unlp.info.bd2.services;

import ar.edu.unlp.info.bd2.exceptions.DeliveryException;
import ar.edu.unlp.info.bd2.model.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface DeliveryService {

	/**
	 * Crea y retorna un usuario de tipo Cliente
	 * @param name nombre y apellido del cliente
	 * @param username nombre de usuario del cliente
	 * @param password clave del cliente
	 * @param email email del cliente
	 * @param dateOfBirth fecha de nacimiento del cliente
	 * @return el cliente creado
	 */
	public Client createClient(String name, String username, String password, String email, Date dateOfBirth) throws DeliveryException;

	/**
	 * Crea y retorna un usuario de tipo Repartidor
	 * @param name nombre y apellido del repartidor
	 * @param username nombre de usuario del repartidor
	 * @param password clave del repartidor
	 * @param email email del repartidor
	 * @param dateOfBirth fecha de nacimiento del repartidor
	 * @return el cliente creado
	 */
	public DeliveryMan createDeliveryMan(String name, String username, String password, String email, Date dateOfBirth) throws DeliveryException;

	/**
	 * Obtiene el usuario (de cualquier tipo) por id
	 * @param id
	 * @return el usuario con el id provisto
	 */
	public Optional<User> getUserById(Long id);

	/**
	 * Obtiene el usuario (de cualquier tipo) por el email
	 * @param email
	 * @return el usuario con el email provisto
	 */
	public Optional<User> getUserByEmail(String email);

	/**
	 * Obtiene un repartidor libre de manera aleatoria
	 * @return el repartidor obtenido
	 */
	public Optional<DeliveryMan> getAFreeDeliveryMan();

	/**
	 * Actualiza los datos de un repartido
	 * @param deliveryMan1 el repartidor a actualizar
	 * @return el repartidor actualizo
	 */
	DeliveryMan updateDeliveryMan(DeliveryMan deliveryMan1) throws DeliveryException;

	/**
	 * Crea y retorna una direccion de entrega de un cliente especifico
	 * @param name titulo de la direccion
	 * @param address direccion indicada
	 * @param apartment numero de departamento
	 * @param coordX latitud de la dirección
	 * @param coordY longuitud de la dirección
	 * @param description detalle que acompaña la direccion
	 * @param client cliente dueño de la dirección
	 * @return la nueva dirección de entrega
	 */
	public Address createAddress(String name, String address, String apartment, float coordX, float coordY, String description, Client client) throws DeliveryException;

	/**
	 * Crea y retorna una direccion de entrega de un cliente especifico (sin numero de departamento)
	 * @param name titulo de la direccion
	 * @param address direccion indicada
	 * @param coordX latitud de la dirección
	 * @param coordY longuitud de la dirección
	 * @param description detalle que acompaña la direccion
	 * @param client cliente dueño de la dirección
	 * @return la nueva dirección de entrega
	 */
	public Address createAddress(String name, String address, float coordX, float coordY, String description, Client client) throws DeliveryException;


	/**
	 * Crea y retorna un nuevo pedido
	 * @param number numero de orden
	 * @param dateOfOrder timestamp de la fecha en que fue realizado el pedido
	 * @param comments comentarios del cliente sobre la orden
	 * @param client cliente que realizó el pedido
	 * @param address dirección en la cual se debe entregar el pedido
	 * @return el nuevo pedido
	 */
	public Order createOrder(int number, Date dateOfOrder, String comments, Client client,  Address address) throws DeliveryException;

	/**
	 * Obtiene el pedido por id
	 * @param id
	 * @return el pedido con el id provisto
	 */
	public Optional<Order> getOrderById(Long id);

	/**
	 * Crea y retorna un nuevo Proveedor
	 * @param name nombre del Proveedor
	 * @param cuil cuil del Proveedor
	 * @param address dirección del Proveedor
	 * @param coordX  coordenada X de la dirección del Proveedor
	 * @param coordY coordeada Y de la dirección del Proveedor
	 * @return el proveedor creado
	 */
	public Supplier createSupplier(String name, String cuil, String address, float coordX, float coordY) throws DeliveryException;

	/**
	 * Obtener y retornar los Suppliers con un nombre
	 * @param name nombre a buscar
	 * @return listado de Suppliers
	 */
	public List<Supplier> getSupplierByName(String name);

	/**
	 * Crea y retorna un nuevo tipo de producto
	 * @param name nombre del tipo de producto
	 * @param description descripcion del tipo de producto
	 * @return el nuevo tipo de producto
	 */
	public ProductType createProductType(String name, String description) throws DeliveryException;

	/**
	 *  Crea y devuelve un nuevo Producto.
	 * @param name nombre del producto a ser creado
	 * @param price precio actual del producto
	 * @param weight peso actual del producto
	 * @param description descripción del producto
	 * @param supplier el productor del producto
	 * @param types listado de los tipos del producto
	 * @return el producto creado
	 */
	public Product createProduct(String name, float price, float weight, String description, Supplier supplier, List<ProductType> types) throws DeliveryException;

	/**
	 *  Crea y devuelve un nuevo Producto.
	 * @param name nombre del producto a ser creado
	 * @param price precio actual del producto
	 * @param lastPriceUpdateDate ultima fecha donde se actualizó el precio del producto
	 * @param weight peso actual del producto
	 * @param description descripción del producto
	 * @param supplier el productor del producto
	 * @param types listado de los tipos del producto
	 * @return el producto creado
	 */
	public Product createProduct(String name, float price, Date lastPriceUpdateDate, float weight, String description, Supplier supplier, List<ProductType> types) throws DeliveryException;

	/**
	 * Obtiene el producto por id
	 * @param id
	 * @return el producto con el id provisto
	 */
	public Optional<Product> getProductById(Long id);

	/**
	 * Obtiene el listado de productos que su nombre contega el string dado
	 * @param name string a buscar
	 * @return Lista de productos
	 */
	public List<Product> getProductByName(String name);

	/**
	 * Obtiene el listado de productos que el nombre de alguno de sus tipo coincide con el string dado
	 * @param type nombre del tipo
	 * @return Lista de productos
	 */
	public List<Product> getProductsByType(String type) throws DeliveryException;

	/**
	 * Actualiza el precio del producto guardando la fecha de la actualización.
	 * @param id id del producto
	 * @param price nuevo precio del producto
	 * @return el producto modificado
	 * @throws DeliveryException en caso de que no exista el producto para el id dado
	 */
	public Product updateProductPrice(Long id, float price) throws DeliveryException;

	/**
	 * Asigna un repartidor a una orden
	 * Se debe verificar si el repartidor esta libre y si la orden no fue entregada
	 * @param order id de orden a ser asignada
	 * @param deliveryMan repartidor a asignar
	 * @return retorna si se pudo hacer la asignación
	 * @throws DeliveryException en caso de no existir el numero de orden
	 */
	public boolean addDeliveryManToOrder(Long order, DeliveryMan deliveryMan) throws DeliveryException;

	/**
	 * Registra que la orden fue entregada y libera al repartidor
	 * @param order id de la orden a actualizar
	 * @return retorno si se pudo actualizar la orden
	 * @throws DeliveryException en caso de no existir el numero de orden
	 */
	public boolean setOrderAsDelivered(Long order) throws DeliveryException;

	/**
	 * Agrega una reseña a una orden
	 * @param order id de orden sobre la que hace la reseña
	 * @param commentary comentario de la reseña
	 * @return la nueva reseña
	 * @throws DeliveryException en caso de no existir el numero de orden
	 */
	public Qualification addQualificatioToOrder(Long order, String commentary) throws DeliveryException ;

	/**
	 * agrega un item al pedido, es decir, una cantidad de un producto
	 * @param order pedido al cual se le agrega el producto
	 * @param quantity cantidad de producto a agregar
	 * @param product producto a agregar
	 * @return el pedido con el nuevo producto
	 * @throws DeliveryException en caso de no existir el pedido
	 */
	public Item addItemToOrder( Long order, Product product,  int quantity, String description ) throws DeliveryException;

}
