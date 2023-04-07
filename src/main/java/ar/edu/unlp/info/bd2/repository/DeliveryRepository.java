package ar.edu.unlp.info.bd2.repository;

import ar.edu.unlp.info.bd2.DeliveryException;
import ar.edu.unlp.info.bd2.model.DeliveryMan;
import ar.edu.unlp.info.bd2.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import ar.edu.unlp.info.bd2.model.DeliveryMan;
import ar.edu.unlp.info.bd2.model.Product;
import ar.edu.unlp.info.bd2.model.ProductType;
import ar.edu.unlp.info.bd2.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class DeliveryRepository {

    @Autowired
    SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(Object obj) throws DeliveryException {
        try{
            this.getSession().save(obj);
        } catch (ConstraintViolationException e) {
            throw new DeliveryException("Constraint Violation");
        } catch (Exception e) {
            throw new DeliveryException(e.getMessage());
        }
    }

    public void update(Object obj) throws DeliveryException {
        try{
            this.getSession().update(obj);
        } catch (ConstraintViolationException e) {
            throw new DeliveryException("Constraint Violation");
        } catch (Exception e) {
            throw new DeliveryException(e.getMessage());
        }
    }

    public Optional<Product> findProductById(Long id) {
        return (Optional<Product>) getSession().createQuery("FROM Product P WHERE P.id = :id")
                .setParameter("id", id)
                .uniqueResultOptional();
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
        return sessionFactory.getCurrentSession().get(User.class, id);
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

    public Product getProductById(Long id){
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    public List<Product> getProductByName(String name) {
        Query<Product> query = getSession().createQuery("select p from Product p where p.name like :name", Product.class);
        query.setParameter("name", '%' + name + '%');
        return query.getResultList();
    }

    public List<Product> getProductsByType(String type){
        Query<Product> query = getSession().createQuery("select p from Product p join p.types t where t.name like :type", Product.class);
        query.setParameter("type", '%' + type + '%');
        List<Product> listpr = query.getResultList();
        return query.getResultList();
    }

}
