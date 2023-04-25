package ar.edu.unlp.info.bd2.services;

import ar.edu.unlp.info.bd2.model.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DeliveryStatisticsService {

    /**
     * Obtiene los N usuarios que poseen mayor puntaje
     * @param n cantidad de usuarios
     * @return una lista con los usuarios
     */
    public List<User> getTopNUserWithMoreScore(int n);

    /**
     * Obtiene los 10 usuarios de tipo Delivery Man que mas ordenes completaron
     * @return el listado de Delivery Man
     */
    public List<DeliveryMan> getTop10DeliveryManWithMoreOrders();

    /**
     * Obtiene una lista de usuarios de tipo Cliente que hicieron al menos una orden con un monto total igual o superior a un valor
     * @param number monto de las ordenes
     * @return el listado de clientes
     */
    public List<Client> getUsersSpentMoreThan(float number);

    /**
     * Obtiene el listado de ordenes realizadas por un cliente
     * @param username nombre de usuario del cliente
     * @return la lista de ordenes
     */
    public List<Order> getAllOrdersFromUser(String username);

    /**
     * Obtiene el numero de ordenes que todavia no fueron completadas, es decir que no fueron entregadas por un Delivery Man
     * @return el numero de ordenes
     */
    public Long getNumberOfOrderNoDelivered();

    /**
     * Obtiene el numero de ordenes completadas, es decir entregadas, en un rango de fechas dadas
     * @param startDate fecha de incio del rango
     * @param endDate fecha final del rango
     * @return el numero de ordenes
     */
    public Long getNumberOfOrderDeliveredAndBetweenDates(Date startDate, Date endDate);

    /**
     * Obtiene la orden completada, es decir entregada por el repartidor, de mayor valor total en un dia dado
     * @param date el dia
     * @return la orden obtenida
     */
    public Optional<Order> getOrderDeliveredMoreExpansiveInDate(Date date);

    /**
     * Obtiene la lista de suppliers que no tienen productos agregados a su catalogo
     * @return la lista de suppliers
     */
    public List<Supplier> getSuppliersWithoutProducts();

    /**
     * Obtiene los productos que no han actualizado su precio en los ultimos N dias
     * @param days cantidad de dias
     * @return el listado de productos
     */
    public List<Product> getProductsWithPriceDateOlderThan(int days);

    /**
     * Obtiene los 5 productos de mayor valor
     * @return el listado de productos
     */
    public List<Product> getTop5MoreExpansiveProducts();

    /**
     * Obtiene el producto más demandado, es decir, aquel que esta se incluyo más veces en ordenes (tener en cuenta la cantidad)
     * @return el producto obtenido
     */
    public Product getMostDemandedProduct();

    /**
     * Obtiene aquellos productos existentes que no fueron incluidos en ninguna orden
     * @return el listado de productos
     */
    public List<Product> getProductsNoAddedToOrders();

    /**
     * Obtiene los 3 tipos de productos que menos productos tienen asociados
     * @return el listado de tipos de producto
     */
    public List<ProductType> getTop3ProductTypesWithLessProducts();

    /**
     * Obtiene el supplier que más productos tiene asociado
     * @return el supplier resultante
     */
    public Supplier getSupplierWithMoreProducts();

    /**
     * Obtiene aquellos suppliers que tienen al menos una calificación de una estrella entre sus ordenes completadas
     * @return el listado de suppliers
     */
    public List<Supplier> getSupplierWith1StarCalifications();

}