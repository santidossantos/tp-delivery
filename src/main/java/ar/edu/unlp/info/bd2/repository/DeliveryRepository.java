package ar.edu.unlp.info.bd2.repository;

import ar.edu.unlp.info.bd2.exceptions.DeliveryException;
import ar.edu.unlp.info.bd2.model.DeliveryMan;
import ar.edu.unlp.info.bd2.model.Product;
import ar.edu.unlp.info.bd2.model.Supplier;
import ar.edu.unlp.info.bd2.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.hibernate.query.Query;
import static ar.edu.unlp.info.bd2.constants.ConstantValues.CONSTRAINT_ERROR;
import static ar.edu.unlp.info.bd2.constants.ConstantValues.USERNAME_ERROR;

@Repository
public class DeliveryRepository {

    @Autowired
    SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(Object obj) throws DeliveryException {
        try {
            this.getSession().save(obj);
        } catch (ConstraintViolationException e) {
            throw new DeliveryException(CONSTRAINT_ERROR);
        } catch (Exception e) {
            throw new DeliveryException(USERNAME_ERROR);
        }
    }

    public void update(Object obj) throws DeliveryException {
        try {
            this.getSession().update(obj);
            this.getSession().getTransaction().commit(); // Tengo que forzar commit para que se guarde en la bd ;(
        } catch (ConstraintViolationException e) {
            throw new DeliveryException(CONSTRAINT_ERROR);
        } catch (Exception e) {
            throw new DeliveryException(USERNAME_ERROR);
        }
    }

    public Product updateProductPrice(Product product) throws DeliveryException {
        this.update(product);
        return product;
    }

    public DeliveryMan updateDeliveryMan(DeliveryMan deliveryMan1) throws DeliveryException {
        this.update(deliveryMan1);
        return deliveryMan1;
    }

    public User getUserById(Long id) {
        return getSession().get(User.class, id);
    }

    public Optional<User> getUserByUsername(String username) {
        Query<User> query = getSession().createQuery("select u from users u where u.username = :username", User.class);
        query.setParameter("username", username);
        return query.uniqueResultOptional();
    }

    public Optional<User> getUserByEmail(String email) {
        Query<User> query = getSession().createQuery("select u from users u where u.email = :email", User.class);
        query.setParameter("email", email);
        return query.uniqueResultOptional();
    }

    public DeliveryMan getFreeDeliveryMan(){
        Query<User> query = getSession().createQuery("select u from users u where u.free = :free", User.class);
        query.setParameter("free", true);
        List<User> list = query.getResultList();
        Random rand = new Random();
        return (DeliveryMan) list.get(rand.nextInt(list.size()));
    }

    public Product getProductById(Long id) {
        return getSession().get(Product.class, id);
    }

    public List<Product> getProductByName(String name) {
        Query<Product> query = getSession().createQuery("select p from Product p where p.name like :name", Product.class);
        query.setParameter("name", '%' + name + '%');
        return query.getResultList();
    }

    public List<Product> getProductsByType(String type) {
        Query<Product> query = getSession().createQuery("select p from Product p join p.types t where t.name like :type", Product.class);
        query.setParameter("type", '%' + type + '%');
        return query.getResultList();
    }

    public List<Supplier> getSupplierByName(String name) {
        Query<Supplier> query = getSession().createQuery("from Supplier s where s.name like :name", Supplier.class);
        query.setParameter("name", '%' + name + '%');
        return query.getResultList();
    }

    public Optional<Supplier> getSupplierByCUIL(String cuit) {
        Query<Supplier> query = getSession().createQuery("from Supplier s where s.cuit = :cuit", Supplier.class);
        query.setParameter("cuit", cuit);
        return query.uniqueResultOptional();
    }

}
