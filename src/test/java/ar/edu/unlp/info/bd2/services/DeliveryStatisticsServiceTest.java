package ar.edu.unlp.info.bd2.services;

import ar.edu.unlp.info.bd2.config.AppConfig;
import ar.edu.unlp.info.bd2.config.DBInitializerConfig;
import ar.edu.unlp.info.bd2.config.HibernateConfiguration;
import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.utils.DBInitializer;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class, HibernateConfiguration.class, DBInitializerConfig.class }, loader = AnnotationConfigContextLoader.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@Rollback(true)
public class DeliveryStatisticsServiceTest {

    @Autowired
    DBInitializer initializer;

    @Autowired
    DeliveryStatisticsService service;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @BeforeAll
    public void prepareDB() throws Exception {
        this.initializer.prepareDB();
    }

    @Test
    void init() {

    }

    @Test
    void testGetTopNUserWithMoreScore() {
        List<User> users = this.service.getTopNUserWithMoreScore(6);
        assertEquals(6, users.size());
        this.assertListEquality(users.stream().map(user -> user.getUsername()).collect(Collectors.toList()), Arrays.asList("laurafernandez","miguelgomez", "isabelcastro","javiermartin", "anaromero","danielsanchez" ));
        List<User> users2 = this.service.getTopNUserWithMoreScore(3);
        this.assertListEquality(users2.stream().map(user -> user.getUsername()).collect(Collectors.toList()), Arrays.asList("miguelgomez", "isabelcastro", "danielsanchez" ));
    }

    @Test
    void testGetTop10DeliveryManWithMoreOrders() {
        List<DeliveryMan> deliveryManList = this.service.getTop10DeliveryManWithMoreOrders();
        assertEquals(10, deliveryManList.size());
        this.assertListEquality(deliveryManList.stream().map(
                        dm -> dm.getUsername()).collect(Collectors.toList()),
                Arrays.asList( "maurito", "carlitos","juanito", "sofi","danielsanchez","anaromero","javiermartin","luisagarcia","marinaperez", "eduardolopez"));
    }

    @Test
    void testGetUsersSpentMoreThan() {
        List<Client> clients = this.service.getUsersSpentMoreThan(25);
        assertEquals(5, clients.size());
        this.assertListEquality(clients.stream().map(
                        client -> client.getUsername()).collect(Collectors.toList()),
                Arrays.asList("juanperez", "mariagarcia", "pedrolopez", "luciarodriguez", "miguelgomez"));
    }

    @Test
    void testGetAllOrdersFromUser() {
        List<Order> orders1 = this.service.getAllOrdersFromUser("pedrolopez");
        assertEquals(5, orders1.size());
        this.assertListEquality(orders1.stream().map(
                        o -> o.getNumber()).collect(Collectors.toList()),
                Arrays.asList(7,8,9,13,23));
        List<Order> orders2 = this.service.getAllOrdersFromUser("isabelcastro");
        assertEquals(2, orders2.size());
        this.assertListEquality(orders2.stream().map(
                        o -> o.getNumber()).collect(Collectors.toList()),
                Arrays.asList(20,30));
    }

    @Test
    void testGetNumberOfOrderNoDelivered() {
        long number = this.service.getNumberOfOrderNoDelivered();
        assertEquals(20, number);
    }

    @Test
    void testGetNumberOfOrderDeliveredAndBetweenDates() {
        LocalDate today = LocalDate.now();
        long number1 = this.service.getNumberOfOrderDeliveredAndBetweenDates(
                java.sql.Date.valueOf(today.minusDays(30)),
                java.sql.Date.valueOf(today.minusDays(20)));
        assertEquals(1, number1);
        long number2 = this.service.getNumberOfOrderDeliveredAndBetweenDates(
                java.sql.Date.valueOf(today.minusDays(10)),
                java.sql.Date.valueOf(today.minusDays(4)));
        assertEquals(5, number2);
    }

    @Test
    void testGetOrderDeliveredMoreExpansiveInDate() {
        LocalDate today = LocalDate.now();
        Optional<Order> order1 = this.service.getOrderDeliveredMoreExpansiveInDate(java.sql.Date.valueOf(today.minusDays(10)));
        assertTrue(order1.isPresent());
        assertEquals(9, order1.get().getNumber());
        Optional<Order> order2 = this.service.getOrderDeliveredMoreExpansiveInDate(java.sql.Date.valueOf(today));
        assertTrue(order2.isPresent());
        assertEquals(2, order2.get().getNumber());
        Optional<Order> order3 = this.service.getOrderDeliveredMoreExpansiveInDate(java.sql.Date.valueOf(today.minusDays(20)));
        assertFalse(order3.isPresent());
    }

    @Test
    void testGetSuppliersWithoutProducts() {
        List<Supplier> suppliers = this.service.getSuppliersWithoutProducts();
        assertEquals(1, suppliers.size());
        assertEquals("Supplier 11", suppliers.get(0).getName());
    }

    @Test
    void testGetProductsWithPriceDateOlderThan() {
        List<Product> products1 = this.service.getProductsWithPriceDateOlderThan(50);
        assertEquals(1, products1.size());
        List<Product> products2 = this.service.getProductsWithPriceDateOlderThan(20);
        assertEquals(4, products2.size());
        List<Product> products3 = this.service.getProductsWithPriceDateOlderThan(10);
        assertEquals(11, products3.size());
    }

    @Test
    void testGetTop5MoreExpansiveProducts() {
        List<Product> products = this.service.getTop5MoreExpansiveProducts();
        assertEquals(5, products.size());
        this.assertListEquality(products.stream().map(
                        (p -> p.getName())).collect(Collectors.toList()),
                Arrays.asList("Pollo Asado", "Sushi Roll", "Hamburguesa", "Spaghetti Bolognese", "Pizza Margarita"));
    }

    @Test
    void testGetMostDemandedProduct() {
        Product product = this.service.getMostDemandedProduct();
        assertEquals("Spaghetti Bolognese", product.getName());
    }

    @Test
    void testGetProductsNoAddedToOrders() {
        List<Product> products = this.service.getProductsNoAddedToOrders();
        assertEquals(5, products.size());
        this.assertListEquality(products.stream().map(
                        (p) -> p.getName()).collect(Collectors.toList()),
                Arrays.asList("Galletitas de avena", "Sandwich de jamón y queso", "Ensalada César", "Muffin de arándanos", "Empanada de carne"));
    }

    @Test
    void testGetTop3ProductTypesWithLessProducts() {
        List<ProductType> types = this.service.getTop3ProductTypesWithLessProducts();
        assertEquals(3, types.size());
        this.assertListEquality(types.stream().map(
                        (t) -> t.getName()).collect(Collectors.toList()),
                Arrays.asList("Pastas", "Sandwiches", "Ensaladas"));
    }

    @Test
    void testGetSupplierWithMoreProducts() {
        Supplier supplier = this.service.getSupplierWithMoreProducts();
        assertEquals("Supplier 4", supplier.getName());
    }

    @Test
    void testGetSupplierWith1StarCalifications() {
        List<Supplier> suppliers = this.service.getSupplierWith1StarCalifications();
        assertEquals(4, suppliers.size());
        this.assertListEquality(suppliers.stream().map(
                        (s) -> s.getName()).collect(Collectors.toList()),
                Arrays.asList("Supplier 1", "Supplier 3", "Supplier 4", "Supplier 5"));
    }

    private <T> void assertListEquality(List<T> list1, List<T> list2) {
        if (list1.size() != list2.size()) {
            Assert.fail("Lists have different size");
        }

        for (T objectInList1 : list1) {
            if (!list2.contains(objectInList1)) {
                Assert.fail(objectInList1 + " is not present in list2");
            }
        }
    }
}