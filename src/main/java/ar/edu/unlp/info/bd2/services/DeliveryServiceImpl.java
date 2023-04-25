package ar.edu.unlp.info.bd2.services;

import ar.edu.unlp.info.bd2.constants.ConstantValues;
import ar.edu.unlp.info.bd2.DeliveryException;
import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repository.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static ar.edu.unlp.info.bd2.constants.ConstantValues.*;


@Service
public class DeliveryServiceImpl implements DeliveryService, DeliveryStatisticsService {

    DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Transactional
    public Client createClient(String name, String username, String password, String email, Date dateOfBirth) throws DeliveryException {
        if (deliveryRepository.getUserByUsername(username.toLowerCase()).isPresent()){
            throw new DeliveryException(ConstantValues.USERNAME_ERROR);
        }
        return (Client) deliveryRepository.save(new Client(name, username, password, email, dateOfBirth));
    }

    @Transactional
    public DeliveryMan createDeliveryMan(String name, String username, String password, String email, Date dateOfBirth) throws DeliveryException {
        if (deliveryRepository.getUserByUsername(username.toLowerCase()).isPresent()) {
            throw new DeliveryException(ConstantValues.USERNAME_ERROR);
        }
        return (DeliveryMan) deliveryRepository.save(new DeliveryMan(name, username, password, email, dateOfBirth));
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable((User) deliveryRepository.getById(id, User.class));
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserByEmail(String email) {
        return deliveryRepository.getUserByEmail(email.toLowerCase());
    }

    @Transactional(readOnly = true)
    public Optional<DeliveryMan> getAFreeDeliveryMan() {
        return Optional.ofNullable(deliveryRepository.getFreeDeliveryMan());
    }

    @Transactional
    public DeliveryMan updateDeliveryMan(DeliveryMan deliveryMan1) throws DeliveryException {
        return (DeliveryMan) deliveryRepository.update(deliveryMan1);
    }

   @Transactional
    public Address createAddress(String name, String address, String apartment, float coordX, float coordY, String description, Client client) throws DeliveryException {
       return (Address) deliveryRepository.save(new Address(name, address, apartment, coordX, coordY, description, client));
    }

    @Transactional
    public Address createAddress(String name, String address, float coordX, float coordY, String description, Client client) throws DeliveryException {
        return (Address) deliveryRepository.save(new Address(name, address, coordX, coordY, description, client));
    }

    @Transactional
    public Order createOrder(int number, Date dateOfOrder, String comments, Client client, Address address) throws DeliveryException {
        Optional<Order> existingOrder = deliveryRepository.getOrderByNumber(number);
        if (existingOrder.isPresent()){
            throw new DeliveryException(CREATE_ORDER_ERROR);
        }
        return (Order) deliveryRepository.save(new Order(number, dateOfOrder, comments, client, address));
    }

    @Transactional(readOnly = true)
    public Optional<Order> getOrderById(Long id) {
        return Optional.ofNullable((Order) deliveryRepository.getById(id, Order.class));
    }

    @Transactional
    public Supplier createSupplier(String name, String cuit, String address, float coordX, float coordY) throws DeliveryException {
        if (deliveryRepository.getSupplierByCUIL(cuit).isPresent()){
            throw new DeliveryException(ConstantValues.USERNAME_ERROR);
        }
        return (Supplier) deliveryRepository.save(new Supplier(name, cuit, address, coordX, coordY));
    }

    @Transactional(readOnly = true)
    public List<Supplier> getSupplierByName(String name) {
        return deliveryRepository.getSupplierByName(name);
    }

    @Transactional
    public ProductType createProductType(String name, String description) throws DeliveryException {
        return (ProductType) deliveryRepository.save(new ProductType(name, description));
    }

    @Transactional
    public Product createProduct(String name, float price, float weight, String description, Supplier supplier, List<ProductType> types) throws DeliveryException {
        return (Product) deliveryRepository.save(new Product(name, price, weight, description, supplier, types));
    }

    @Transactional
    public Product createProduct(String name, float price, Date lastPriceUpdateDate, float weight, String description, Supplier supplier, List<ProductType> types) throws DeliveryException {
        return (Product) deliveryRepository.save(new Product(name, price, lastPriceUpdateDate, weight, description, supplier, types));
    }

    @Transactional(readOnly = true)
    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable((Product) deliveryRepository.getById(id, Product.class));
    }

    @Transactional(readOnly = true)
    public List<Product> getProductByName(String name) {
        return deliveryRepository.getProductByName(name);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByType(String type) throws DeliveryException {
        List<Product> productsByType = deliveryRepository.getProductsByType(type);
        if (productsByType.isEmpty()) throw new DeliveryException(ConstantValues.PRODUCT_ERROR);
        return productsByType;
    }

    @Transactional
    public Product updateProductPrice(Long id, float price) throws DeliveryException {
        Optional<Product> product = this.getProductById(id);
        if (!product.isPresent()) {
            throw new DeliveryException(UPDATE_PRODUCT_ERROR);
        }

        Product product1 = product.get();
        product1.setPrice(price);
        product1.setLastPriceUpdateDate(new Date());
        return (Product) this.deliveryRepository.update(product.get());
    }

    @Transactional
    public boolean addDeliveryManToOrder(Long order, DeliveryMan deliveryMan) throws DeliveryException {
        Order order1 = (Order) this.deliveryRepository.getById(order, Order.class);
        checkExistence(order1, ORDER_ERROR);
        if (checkAddDeliveryManToOrderConditions(deliveryMan, order1)) return false;
        order1.setDeliveryMan(deliveryMan);
        deliveryMan.setFree(false);
        this.deliveryRepository.update(order1);
        return true;
    }

    private static boolean checkAddDeliveryManToOrderConditions(DeliveryMan deliveryMan, Order order1) {
        return order1.getItems().size() == 0 || !deliveryMan.isFree();
    }

    private static void checkExistence(Object obj, String error) throws DeliveryException {
        if(obj == null){
            throw new DeliveryException(error);
        }
    }

    @Transactional
    public boolean setOrderAsDelivered(Long order) throws DeliveryException {
        Order order1 = (Order) this.deliveryRepository.getById(order, Order.class);
        checkExistence(order1, ORDER_ERROR);
        if(order1.getDeliveryMan() == null){
            return  false;
        }

        order1.setDelivered(true);

        DeliveryMan deliveryMan = order1.getDeliveryMan();
        deliveryMan.setFree(true);
        deliveryMan.setScore(deliveryMan.getScore() + 1);
        deliveryMan.setNumberOfSuccessOrders(deliveryMan.getNumberOfSuccessOrders() + 1);

        Client client = order1.getClient();
        client.setScore(client.getScore() + 1);

        this.deliveryRepository.update(order1);  // Hace update en cascada tambien de cliente y deliveryMan por como estan definidas las relaciones
        return true;
    }

    @Transactional
    public Qualification addQualificatioToOrder(Long order, String commentary) throws DeliveryException {
        Order order1 = (Order) this.deliveryRepository.getById(order, Order.class);
        checkExistence(order1, ORDER_ERROR);
        if(!order1.isDelivered()){
            throw new DeliveryException(ORDER_ERROR);
        }

        Qualification qualification = new Qualification(0, commentary, order1);
        order1.setQualification(qualification);
        this.deliveryRepository.save(qualification);
        return qualification;
    }

    @Transactional
    public Item addItemToOrder(Long order, Product product, int quantity, String description) throws DeliveryException {
        Order ord = (Order) deliveryRepository.getById(order, Order.class);
        checkExistence(ord, ORDER_ERROR);
        checkIfAnOrderIsDelivered(ord);

        Item item = new Item(ord, product, quantity, description);
        ord.addItem(item);
        ord.setTotalPrice(ord.getTotalPrice() + item.getProduct().getPrice() * item.getQuantity());
        this.deliveryRepository.save(item);
        return item;
    }

    private void checkIfAnOrderIsDelivered(Order order) throws DeliveryException {
        if(order.isDelivered()){
            throw new DeliveryException(ORDER_ERROR);
        }
    }

    /*
            PARTE 2
     */

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
