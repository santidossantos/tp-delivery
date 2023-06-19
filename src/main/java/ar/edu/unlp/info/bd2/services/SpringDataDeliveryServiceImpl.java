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
import static ar.edu.unlp.info.bd2.constants.ConstantValues.*;

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

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;


    @Transactional
    public Client createClient(String name, String username, String password, String email, Date dateOfBirth) throws DeliveryException {
        return clientRepository.save(new Client(name, username, password, email, dateOfBirth));
    }

    @Transactional
    public DeliveryMan createDeliveryMan(String name, String username, String password, String email, Date dateOfBirth) throws DeliveryException {
        return deliveryManRepository.save(new DeliveryMan(name, username, password, email, dateOfBirth));
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.empty();
    }

    @Transactional(readOnly = true)
    public Optional<DeliveryMan> getAFreeDeliveryMan() {
        return Optional.ofNullable(deliveryManRepository.findByFreeTrue().orElse(null));
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

    @Transactional
    public Order createOrder(int number, Date dateOfOrder, String comments, Client client, Address address) throws DeliveryException {
        if(orderRepository.existsByNumber(number)) throw new DeliveryException(CONSTRAINT_ERROR);
        return orderRepository.save(new Order(number, dateOfOrder, comments, client, address));
    }

    @Transactional(readOnly = true)
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Supplier createSupplier(String name, String cuit, String address, float coordX, float coordY) throws DeliveryException {
        if(supplierRepository.existsByCuit(cuit)) throw new DeliveryException(CONSTRAINT_ERROR);
        return supplierRepository.save(new Supplier(name, cuit, address, coordX, coordY));
    }

    @Transactional(readOnly = true)
    public List<Supplier> getSupplierByName(String name) {
        return supplierRepository.findByNameContaining(name);
    }

    @Transactional
    public ProductType createProductType(String name, String description) throws DeliveryException {
        return productTypeRepository.save(new ProductType(name, description));
    }

    @Transactional
    public Product createProduct(String name, float price, float weight, String description, Supplier supplier, List<ProductType> types) throws DeliveryException {
        return productRepository.save(new Product(name, price, weight, description, supplier, types));
    }

    @Transactional
    public Product createProduct(String name, float price, Date lastPriceUpdateDate, float weight, String description, Supplier supplier, List<ProductType> types) throws DeliveryException {
        return productRepository.save(new Product(name, price, lastPriceUpdateDate, weight, description, supplier, types));
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

    @Transactional
    public Product updateProductPrice(Long id, float price) throws DeliveryException {
        Product product = this.getProductById(id).orElseThrow(() -> new DeliveryException(UPDATE_PRODUCT_ERROR));
        product.updateProductPrice(price);
        return this.productRepository.save(product);
    }

    @Transactional
    public boolean addDeliveryManToOrder(Long order, DeliveryMan deliveryMan) throws DeliveryException {
        Order order1 = (Order) this.orderRepository.findById(order).orElseThrow(() -> new DeliveryException(ORDER_ERROR));
        if (checkAddDeliveryManToOrderConditions(deliveryMan, order1)) return false;
        order1.setDeliveryMan(deliveryMan);
        this.orderRepository.save(order1);
        return true;
    }

    private static boolean checkAddDeliveryManToOrderConditions(DeliveryMan deliveryMan, Order order1) {
        return order1.getItems().size() == 0 || !deliveryMan.isFree();
    }

    @Transactional
    public boolean setOrderAsDelivered(Long order) throws DeliveryException {
        Order order1 = (Order) this.orderRepository.findById(order).orElseThrow(() -> new DeliveryException(ORDER_ERROR));
        if(!order1.hasAsignedDeliveryMan()) return false;
        order1.setDelivered(true);
        this.orderRepository.save(order1);  // Hace update en cascada tambien de cliente y deliveryMan por como estan definidas las relaciones
        return true;
    }

    @Override
    public Qualification addQualificatioToOrder(Long order, String commentary) throws DeliveryException {
        return null;
    }

    @Transactional
    public Item addItemToOrder(Long order, Product product, int quantity, String description) throws DeliveryException {
        Order ord = orderRepository.findById(order).orElseThrow(() -> new DeliveryException(ORDER_ERROR));
        if(ord.isDelivered()) throw new DeliveryException(ORDER_ERROR);
        Item item = new Item(ord, product, quantity, description);
        ord.addItem(item);
        itemRepository.save(item);
        return item;
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
