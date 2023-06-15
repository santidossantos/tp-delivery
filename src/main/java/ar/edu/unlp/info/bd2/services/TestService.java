package ar.edu.unlp.info.bd2.services;

import ar.edu.unlp.info.bd2.DeliveryException;
import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repository.ClientRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TestService implements DeliveryService, DeliveryStatisticsService{
    ClientRepository clientRepository;

    @Transactional
    public Client createClient(String name, String username, String password, String email, Date dateOfBirth) throws DeliveryException {
        return (Client)
                clientRepository.save
                        (new Client(name, username, password, email, dateOfBirth));
    }

    @Override
    public DeliveryMan createDeliveryMan(String name, String username, String password, String email, Date dateOfBirth) throws DeliveryException {
        return null;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<DeliveryMan> getAFreeDeliveryMan() {
        return Optional.empty();
    }

    @Override
    public DeliveryMan updateDeliveryMan(DeliveryMan deliveryMan1) throws DeliveryException {
        return null;
    }

    @Override
    public Address createAddress(String name, String address, String apartment, float coordX, float coordY, String description, Client client) throws DeliveryException {
        return null;
    }

    @Override
    public Address createAddress(String name, String address, float coordX, float coordY, String description, Client client) throws DeliveryException {
        return null;
    }

    @Override
    public Order createOrder(int number, Date dateOfOrder, String comments, Client client, Address address) throws DeliveryException {
        return null;
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return Optional.empty();
    }

    @Override
    public Supplier createSupplier(String name, String cuil, String address, float coordX, float coordY) throws DeliveryException {
        return null;
    }

    @Override
    public List<Supplier> getSupplierByName(String name) {
        return null;
    }

    @Override
    public ProductType createProductType(String name, String description) throws DeliveryException {
        return null;
    }

    @Override
    public Product createProduct(String name, float price, float weight, String description, Supplier supplier, List<ProductType> types) throws DeliveryException {
        return null;
    }

    @Override
    public Product createProduct(String name, float price, Date lastPriceUpdateDate, float weight, String description, Supplier supplier, List<ProductType> types) throws DeliveryException {
        return null;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> getProductByName(String name) {
        return null;
    }

    @Override
    public List<Product> getProductsByType(String type) throws DeliveryException {
        return null;
    }

    @Override
    public Product updateProductPrice(Long id, float price) throws DeliveryException {
        return null;
    }

    @Override
    public boolean addDeliveryManToOrder(Long order, DeliveryMan deliveryMan) throws DeliveryException {
        return false;
    }

    @Override
    public boolean setOrderAsDelivered(Long order) throws DeliveryException {
        return false;
    }

    @Override
    public Qualification addQualificatioToOrder(Long order, String commentary) throws DeliveryException {
        return null;
    }

    @Override
    public Item addItemToOrder(Long order, Product product, int quantity, String description) throws DeliveryException {
        return null;
    }

    @Override
    public User updateUser(User user) throws DeliveryException {
        return null;
    }

    @Override
    public Qualification updateQualification(Qualification qualification) throws DeliveryException {
        return null;
    }

    @Override
    public List<User> getTopNUserWithMoreScore(int n) {
        return null;
    }

    @Override
    public List<DeliveryMan> getTop10DeliveryManWithMoreOrders() {
        return null;
    }

    @Override
    public List<Client> getUsersSpentMoreThan(float number) {
        return null;
    }

    @Override
    public List<Order> getAllOrdersFromUser(String username) {
        return null;
    }

    @Override
    public Long getNumberOfOrderNoDelivered() {
        return null;
    }

    @Override
    public Long getNumberOfOrderDeliveredAndBetweenDates(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public Optional<Order> getOrderDeliveredMoreExpansiveInDate(Date date) {
        return Optional.empty();
    }

    @Override
    public List<Supplier> getSuppliersWithoutProducts() {
        return null;
    }

    @Override
    public List<Product> getProductsWithPriceDateOlderThan(int days) {
        return null;
    }

    @Override
    public List<Product> getTop5MoreExpansiveProducts() {
        return null;
    }

    @Override
    public Product getMostDemandedProduct() {
        return null;
    }

    @Override
    public List<Product> getProductsNoAddedToOrders() {
        return null;
    }

    @Override
    public List<ProductType> getTop3ProductTypesWithLessProducts() {
        return null;
    }

    @Override
    public Supplier getSupplierWithMoreProducts() {
        return null;
    }

    @Override
    public List<Supplier> getSupplierWith1StarCalifications() {
        return null;
    }
}
