package ar.edu.unlp.info.bd2.services;

import ar.edu.unlp.info.bd2.constants.ConstantValues;
import ar.edu.unlp.info.bd2.exceptions.DeliveryException;
import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repository.DeliveryRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static ar.edu.unlp.info.bd2.constants.ConstantValues.*;


@Service
public class DeliveryServiceImpl implements DeliveryService {

    DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Transactional
    public Client createClient(String name, String username, String password, String email, Date dateOfBirth) throws DeliveryException {
        if (deliveryRepository.getUserByUsername(username.toLowerCase()).isPresent())
            throw new DeliveryException(ConstantValues.USERNAME_ERROR);

        Client client = new Client(name, username, password, email, dateOfBirth);
        this.deliveryRepository.save(client);
        return client;
    }

    @Transactional
    public DeliveryMan createDeliveryMan(String name, String username, String password, String email, Date dateOfBirth) throws DeliveryException {
        if (deliveryRepository.getUserByUsername(username.toLowerCase()).isPresent())
            throw new DeliveryException(ConstantValues.USERNAME_ERROR);

        DeliveryMan deliveryMan = new DeliveryMan(name, username, password, email, dateOfBirth);
        this.deliveryRepository.save(deliveryMan);
        return deliveryMan;
    }

    @Transactional
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(deliveryRepository.getUserById(id));
    }

    @Transactional
    public Optional<User> getUserByEmail(String email) {
        return deliveryRepository.getUserByEmail(email.toLowerCase());
    }

    @Transactional
    public Optional<DeliveryMan> getAFreeDeliveryMan() {
        return Optional.ofNullable(deliveryRepository.getFreeDeliveryMan());
    }

    @Transactional
    public DeliveryMan updateDeliveryMan(DeliveryMan deliveryMan1) throws DeliveryException {
        this.deliveryRepository.update(deliveryMan1);
        return deliveryMan1;
    }

   @Transactional
    public Address createAddress(String name, String address, String apartment, float coordX, float coordY, String description, Client client) throws DeliveryException {
       Address anAddress = new Address(name, address, apartment, coordX, coordY, description, client);
       this.deliveryRepository.save(anAddress);
       return anAddress;
    }

    @Transactional
    public Address createAddress(String name, String address, float coordX, float coordY, String description, Client client) throws DeliveryException {
        Address anAddress = new Address(name, address, coordX, coordY, description, client);
        this.deliveryRepository.save(anAddress);
        return anAddress;
    }

    @Transactional
    public Order createOrder(int number, Date dateOfOrder, String comments, Client client, Address address) throws DeliveryException {
        Optional<Order> existingOrder = this.deliveryRepository.getOrderByNumber(number);
        if (existingOrder.isPresent()){
            throw new DeliveryException(CREATE_ORDER_ERROR);
        }
        Order AnOrder = new Order(number, dateOfOrder, comments, client, address);
        this.deliveryRepository.save(AnOrder);
        return AnOrder;
    }


    public Optional<Order> getOrderById(Long id) {
        return Optional.ofNullable(deliveryRepository.getOrderById(id));
    }

    @Transactional
    public Supplier createSupplier(String name, String cuit, String address, float coordX, float coordY) throws DeliveryException {
        if (deliveryRepository.getSupplierByCUIL(cuit).isPresent()){
            throw new DeliveryException(ConstantValues.USERNAME_ERROR);
        }
        Supplier supplier = new Supplier(name, cuit, address, coordX, coordY);
        this.deliveryRepository.save(supplier);
        return supplier;
    }

    public List<Supplier> getSupplierByName(String name) {
        return deliveryRepository.getSupplierByName(name);
    }

    @Transactional
    public ProductType createProductType(String name, String description) throws DeliveryException {
        ProductType productType = new ProductType(name, description);
        this.deliveryRepository.save(productType);
        return productType;
    }

    @Transactional
    public Product createProduct(String name, float price, float weight, String description, Supplier supplier, List<ProductType> types) throws DeliveryException {
        Product product = new Product(name, price, weight, description, supplier, types);
        this.deliveryRepository.save(product);
        return product;
    }

    @Transactional
    public Product createProduct(String name, float price, Date lastPriceUpdateDate, float weight, String description, Supplier supplier, List<ProductType> types) throws DeliveryException {
        Product product = new Product(name, price, lastPriceUpdateDate, weight, description, supplier, types);
        this.deliveryRepository.save(product);
        return product;
    }

    @Transactional
    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(deliveryRepository.getProductById(id));
    }

    @Transactional
    public List<Product> getProductByName(String name) {
        return deliveryRepository.getProductByName(name);
    }

    @Transactional
    public List<Product> getProductsByType(String type) throws DeliveryException {
        List<Product> productsByType = deliveryRepository.getProductsByType(type);
        if (productsByType == null || productsByType.isEmpty()) throw new DeliveryException(ConstantValues.PRODUCT_ERROR);
        return productsByType;
    }

    @Transactional
    public Product updateProductPrice(Long id, float price) throws DeliveryException {
        Optional<Product> product = this.getProductById(id);
        if (!product.isPresent()) {
            throw new DeliveryException(UPDATE_PRODUCT_ERROR);
        }
        product.get().setPrice(price);
        return this.deliveryRepository.updateProductPrice(product.get());
    }

    @Transactional
    public boolean addDeliveryManToOrder(Long order, DeliveryMan deliveryMan) throws DeliveryException {
        Order order1 = this.deliveryRepository.getOrderById(order);
        if(order1 == null){
            throw new DeliveryException(ORDER_ERROR);
        }
        if(order1.getItems().size() == 0 || !deliveryMan.isFree()) {
            return false;
        }
        order1.setDeliveryMan(deliveryMan);
        deliveryMan.setFree(false);
        this.deliveryRepository.update(order1);
        return true;
    }

    @Transactional
    public boolean setOrderAsDelivered(Long order) throws DeliveryException {
        Order order1 = this.deliveryRepository.getOrderById(order);
        if(order1 == null){
            throw new DeliveryException(ORDER_ERROR);
        }
        if(order1.getDeliveryMan() == null){
            return  false;
        }
        order1.setDelivered(true);
        DeliveryMan deliveryMan = order1.getDeliveryMan();   // TODA ESTA LOGICA VA EN EL SERVICIO?
        deliveryMan.setFree(true);
        //deliveryMan.setScore((int) (deliveryMan.getScore() + order1.getQualification().getScore()));
        deliveryMan.setScore(1);
        deliveryMan.setNumberOfSuccessOrders(deliveryMan.getNumberOfSuccessOrders() + 1);

        Client client = order1.getClient();
        client.setScore(client.getScore() + 1);  // PREGUNTAR

        this.deliveryRepository.update(order1);
        this.deliveryRepository.update(deliveryMan);
        this.deliveryRepository.update(client);
        return true;
    }

    // SE COMIERON PASAR LAS ESTRELLAS?
    @Transactional
    public Qualification addQualificatioToOrder(Long order, String commentary) throws DeliveryException {
        Order order1 = this.deliveryRepository.getOrderById(order);
        if(order1 == null || !order1.isDelivered()){
            throw new DeliveryException(ORDER_ERROR);
        }
        Qualification qualification = new Qualification(0, commentary, order1);
        order1.setQualification(qualification); // RARO EN EL SERVICIO?
        this.deliveryRepository.save(qualification);
        return qualification;
    }

    @Transactional
    public Item addItemToOrder(Long order, Product product, int quantity, String description) throws DeliveryException {
        Order ord = deliveryRepository.getOrderById(order);
        if (ord == null) {
            throw new DeliveryException("No existe una orden con el id " + order);
        }

        if (ord.isDelivered()) {
            throw new DeliveryException("La orden con id " + order + " ya fue entregada y no se pueden agregar más items.");
        }

        Item item = new Item(ord, product, quantity, description);
        ord.addItem(item);
        float totalPrice = ord.getTotalPrice() + item.getProduct().getPrice() * item.getQuantity();
        ord.setTotalPrice(totalPrice);
        this.deliveryRepository.save(item);
        return item;
    }

}
