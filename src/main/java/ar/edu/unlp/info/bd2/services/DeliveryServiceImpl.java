package ar.edu.unlp.info.bd2.services;

import ar.edu.unlp.info.bd2.DeliveryException;
import ar.edu.unlp.info.bd2.model.*;
import ar.edu.unlp.info.bd2.repository.DeliveryRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Transactional
    public Client createClient(String name, String username, String password, String email, Date dateOfBirth) throws DeliveryException {
        Client client = new Client(name, username, password, email, dateOfBirth);
        deliveryRepository.save(client);
        return client;
    }

    public DeliveryMan createDeliveryMan(String name, String username, String password, String email, Date dateOfBirth) throws DeliveryException {
        return null;
    }

    public Optional<User> getUserById(Long id) {
        return Optional.empty();
    }

    public Optional<User> getUserByEmail(String email) {
        return Optional.empty();
    }

    public Optional<DeliveryMan> getAFreeDeliveryMan() {
        return Optional.empty();
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

    @Transactional
    public Product updateProductPrice(Long id, float price) throws DeliveryException {
        Optional<Product> product = this.deliveryRepository.findProductById(id);
        if (!product.isPresent()) {
            throw new DeliveryException("Product not found");
        }
        product.get().setPrice(price);
        return this.deliveryRepository.updateProductPrice(product.get());
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
