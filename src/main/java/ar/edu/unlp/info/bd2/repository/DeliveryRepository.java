package ar.edu.unlp.info.bd2.repository;

import ar.edu.unlp.info.bd2.DeliveryException;
import ar.edu.unlp.info.bd2.model.*;
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
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Object save(Object obj) throws DeliveryException {
        try {
            this.getSession().save(obj);
        } catch (Exception e) {
            handleException(e);
        }
        return obj;
    }

    public Object update(Object obj) throws DeliveryException {
        try {
            this.getSession().update(obj);
        } catch (Exception e) {
            handleException(e);
        }
        return obj;
    }

    private void handleException(Exception e) throws DeliveryException {
        if (e instanceof ConstraintViolationException) {
            throw new DeliveryException(CONSTRAINT_ERROR);
        } else {
            throw new DeliveryException(USERNAME_ERROR);
        }
    }

    public Object getById(Long id, Class c) {
        return getSession().get(c, id);
    }

    public Optional<User> getUserByUsername(String username) {
        Query<User> query = getSession().createQuery("from User where username = :username", User.class);
        query.setParameter("username", username);
        return query.uniqueResultOptional();
    }

    public Optional<User> getUserByEmail(String email) {
        Query<User> query = getSession().createQuery("from User where email = :email", User.class);
        query.setParameter("email", email);
        return query.uniqueResultOptional();
    }

    public DeliveryMan getFreeDeliveryMan(){
        Query<User> query = getSession().createQuery("from DeliveryMan where free = :free", User.class);
        query.setParameter("free", true);
        List<User> list = query.getResultList();
        if(list.size() > 0) {
            return (DeliveryMan) list.get(new Random().nextInt(list.size()));
        }
        return null;
    }

    public List<Product> getProductByName(String name) {
        Query<Product> query = getSession().createQuery("from Product where name like :name", Product.class);
        query.setParameter("name", '%' + name + '%');
        return query.getResultList();
    }

    public List<Product> getProductsByType(String type) {
        Query<Product> query = getSession().createQuery("select p from Product p join p.types t where t.name like :type", Product.class);
        query.setParameter("type", '%' + type + '%');
        return query.getResultList();
    }

    public List<Supplier> getSupplierByName(String name) {
        Query<Supplier> query = getSession().createQuery("from Supplier where name like :name", Supplier.class);
        query.setParameter("name", '%' + name + '%');
        return query.getResultList();
    }

    public Optional<Supplier> getSupplierByCUIL(String cuit) {
        Query<Supplier> query = getSession().createQuery("from Supplier where cuit = :cuit", Supplier.class);
        query.setParameter("cuit", cuit);
        return query.uniqueResultOptional();
    }

    public Optional<Order> getOrderByNumber(int number){
        Query<Order> query = getSession().createQuery("from Order where number = :number", Order.class);
        query.setParameter("number", number);
        return query.uniqueResultOptional();
    }

    /*                          *
     *          PARTE 2         *
     *                          */

    private <T> Query<T> simpleQueryFactory(String hql, Class<T> type) {
        return getSession().createQuery(hql, type);
    }

    public List<User> getTopNUserWithMoreScore(int n) {
        return simpleQueryFactory("from User order by score desc", User.class).setMaxResults(n).getResultList();
    }

    public List<DeliveryMan> getTop10DeliveryManWithMoreOrders(int n) {
        return simpleQueryFactory("from DeliveryMan order by number_of_success_orders", DeliveryMan.class)
                .setMaxResults(n).getResultList();
    }
    
    public List<Client> getUsersSpentMoreThan(float number) {
        return  simpleQueryFactory("from Client c join c.orders o where o.total_price >= :number", Client.class)
                .setParameter("number", number).getResultList();
    }

    public List<Order> getAllOrdersFromUser(String username) {
        return  simpleQueryFactory("from Order o join o.client c where c.username = :username", Order.class)
                .setParameter("username", username).getResultList();
    }

    public Long getNumberOfOrderNoDelivered() {
        return simpleQueryFactory("select count(id) from Order where delivered = false", Order.class).stream().count();
    }

}
