package ar.edu.unlp.info.bd2.services;

import ar.edu.unlp.info.bd2.DeliveryException;
import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static ar.edu.unlp.info.bd2.constants.ConstantValues.PRODUCT_ERROR;

@Service
public class SpringDataDeliveryServiceImpl implements DeliveryService, DeliveryStatisticsService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DeliveryManRepository deliveryManRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    SupplierRepository supplierRepository;


    @Transactional
    public Client createClient(String name, String username, String password, String email, Date dateOfBirth) throws DeliveryException {
        return (Client)
                clientRepository.save
                        (new Client(name, username, password, email, dateOfBirth));
    }


    @Transactional
    public DeliveryMan createDeliveryMan(String name, String username, String password, String email, Date dateOfBirth) throws DeliveryException {
        return (DeliveryMan) deliveryManRepository.save(new DeliveryMan(name, username, password, email, dateOfBirth));
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userRepository.getById(id).map(user -> (User) user);
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

    @Transactional
    public Address createAddress(String name, String address, String apartment, float coordX, float coordY, String description, Client client) throws DeliveryException {
        return addressRepository.save(new Address(name, address, apartment, coordX, coordY, description, client));
    }

    @Transactional
    public Address createAddress(String name, String address, float coordX, float coordY, String description, Client client) throws DeliveryException {
        return addressRepository.save(new Address(name, address, coordX, coordY, description, client));
    }

    @Override
    public Order createOrder(int number, Date dateOfOrder, String comments, Client client, Address address) throws DeliveryException {
        return null;
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return Optional.empty();
    }

    @Transactional
    public Supplier createSupplier(String name, String cuit, String address, float coordX, float coordY) throws DeliveryException {
        return supplierRepository.save(new Supplier(name, cuit, address, coordX, coordY));
    }


    @Override
    public List<Supplier> getSupplierByName(String name) {
        return null;
    }

    @Transactional
    public ProductType createProductType(String name, String description) throws DeliveryException {
        return (ProductType) productTypeRepository.save(new ProductType(name, description));
    }

    @Transactional
    public Product createProduct(String name, float price, float weight, String description, Supplier supplier, List<ProductType> types) throws DeliveryException {
        return (Product) productRepository.save(new Product(name, price, weight, description, supplier, types));
    }

    @Transactional
    public Product createProduct(String name, float price, Date lastPriceUpdateDate, float weight, String description, Supplier supplier, List<ProductType> types) throws DeliveryException {
        return (Product) productRepository.save(new Product(name, price, lastPriceUpdateDate, weight, description, supplier, types));
    }

    @Transactional(readOnly = true)
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByType(String type) throws DeliveryException {
        List<Product> productsByType = productRepository.findByTypesName(type);
        if (productsByType.isEmpty()) throw new DeliveryException(PRODUCT_ERROR);
        return productsByType;
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
