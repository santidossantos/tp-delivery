package ar.edu.unlp.info.bd2.services;

import ar.edu.unlp.info.bd2.constants.ConstantValues;
import ar.edu.unlp.info.bd2.exceptions.DeliveryException;
import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repository.DeliveryRepository;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


@Service
public class DeliveryServiceImpl implements DeliveryService {

    DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl() {}

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Transactional
    public Client createClient(String name, String username, String password, String email, Date dateOfBirth) throws DeliveryException {
        if (deliveryRepository.getUserByUsername(username.toLowerCase()).isPresent())
            throw new DeliveryException(ConstantValues.ERROR_USERNAME);

        Client client = new Client(name, username, password, email, dateOfBirth);
        this.deliveryRepository.save(client);
        return client;
    }

    @Transactional
    public DeliveryMan createDeliveryMan(String name, String username, String password, String email, Date dateOfBirth) throws DeliveryException {
        if (deliveryRepository.getUserByUsername(username.toLowerCase()).isPresent())
            throw new DeliveryException(ConstantValues.ERROR_USERNAME);

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

    public Optional<DeliveryMan> getAFreeDeliveryMan() {
        return Optional.ofNullable(deliveryRepository.getFreeDeliveryMan());
    }

    public DeliveryMan updateDeliveryMan(DeliveryMan deliveryMan1) throws DeliveryException {
        return null;
    }

   @Transactional
    public Address createAddress(String name, String address, String apartment, float coordX, float coordY, String description, Client client) throws DeliveryException {
       Address anAddress = new Address(name, address, apartment, coordX, coordY, description, client);
       this.deliveryRepository.save(anAddress);
       return anAddress;
    }

    /**
     * Es lo mismo que el de arriba sin el departamento
     */
    @Transactional
    public Address createAddress(String name, String address, float coordX, float coordY, String description, Client client) throws DeliveryException {
        Address anAddress = new Address(name, address, coordX, coordY, description, client);
        this.deliveryRepository.save(anAddress);
        return anAddress;
    }

    public Order createOrder(int number, Date dateOfOrder, String comments, Client client, Address address) throws DeliveryException {
        return null;
    }

    public Optional<Order> getOrderById(Long id) {
        return Optional.empty();
    }

    public Supplier createSupplier(String name, String cuil, String address, float coordX, float coordY) throws DeliveryException {
        return null;
    }

    public List<Supplier> getSupplierByName(String name) {
        return null;
    }

    public ProductType createProductType(String name, String description) throws DeliveryException {
        return null;
    }

    public Product createProduct(String name, float price, float weight, String description, Supplier supplier, List<ProductType> types) throws DeliveryException {
        return null;
    }

    public Product createProduct(String name, float price, Date lastPriceUpdateDate, float weight, String description, Supplier supplier, List<ProductType> types) throws DeliveryException {
        return null;
    }

    public Optional<Product> getProductById(Long id) {
        return Optional.empty();
    }

    public List<Product> getProductByName(String name) {
        return null;
    }

    public List<Product> getProductsByType(String type) throws DeliveryException {
        return null;
    }

    public Product updateProductPrice(Long id, float price) throws DeliveryException {
        return null;
    }

    public boolean addDeliveryManToOrder(Long order, DeliveryMan deliveryMan) throws DeliveryException {
        return false;
    }

    public boolean setOrderAsDelivered(Long order) throws DeliveryException {
        return false;
    }

    public Qualification addQualificatioToOrder(Long order, String commentary) throws DeliveryException {
        return null;
    }

    public Item addItemToOrder(Long order, Product product, int quantity, String description) throws DeliveryException {
        return null;
    }

}
