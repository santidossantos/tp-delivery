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

    private <T> Query<T> simpleQueryFactory(String hql, Class<T> type) {
        return getSession().createQuery(hql, type);
    }

    public Optional<Object> getById(Long id, Class c) {
        return Optional.ofNullable(getSession().get(c, id));
    }

    public Optional<User> getUserByUsername(String username) {
        return simpleQueryFactory("from User where username = :username", User.class)
                .setParameter("username", username).uniqueResultOptional();
    }

    public Optional<User> getUserByEmail(String email) {
        return  simpleQueryFactory("from User where email = :email", User.class)
                .setParameter("email", email).uniqueResultOptional();
    }

    public DeliveryMan getFreeDeliveryMan(){
        List<User> list  = simpleQueryFactory("from DeliveryMan where free = :free", User.class)
                .setParameter("free", true).getResultList();

        return (list.size() > 0) ? ((DeliveryMan) list.get(new Random().nextInt(list.size()))) : null;
    }

    public List<Product> getProductByName(String name) {
        return  simpleQueryFactory("from Product where name like :name", Product.class)
                .setParameter("name", '%' + name + '%').getResultList();
    }

    public List<Product> getProductsByType(String type) {
        return  simpleQueryFactory("select p from Product p join p.types t where t.name like :type", Product.class)
                .setParameter("type", '%' + type + '%').getResultList();
    }

    public List<Supplier> getSupplierByName(String name) {
        return  simpleQueryFactory("from Supplier where name like :name", Supplier.class)
                .setParameter("name", '%' + name + '%').getResultList();
    }

    public Optional<Supplier> getSupplierByCUIL(String cuit) {
        return simpleQueryFactory("from Supplier where cuit = :cuit", Supplier.class)
                .setParameter("cuit", cuit).uniqueResultOptional();
    }

    public Optional<Order> getOrderByNumber(int number){
        return simpleQueryFactory("from Order where number = :number", Order.class)
                .setParameter("number", number).uniqueResultOptional();
    }

    public List<User> getTopNUserWithMoreScore(int n) {
        return simpleQueryFactory("from User order by score desc", User.class).setMaxResults(n).getResultList();
    }

    public List<DeliveryMan> getTopNDeliveryManWithMoreOrders(int n) {
        return simpleQueryFactory("from DeliveryMan order by numberOfSuccessOrders desc", DeliveryMan.class)
                .setMaxResults(n).getResultList();
    }
    
    /*public List<Client> getUsersSpentMoreThan(float number) {
        return  simpleQueryFactory("select distinct c from Client c join c.orders o where o.totalPrice >= :number", Client.class)
                .setParameter("number", number).getResultList();
    }*/

    public List<Client> getUsersSpentMoreThan(float number) {
        return  simpleQueryFactory("select distinct c from Order o join o.client c where o.totalPrice >= :number", Client.class)
                .setParameter("number", number).getResultList();
    }

    public List<Order> getAllOrdersFromUser(String username) {
        return  simpleQueryFactory("select o from Order o join o.client c where c.username = :username", Order.class)
                .setParameter("username", username).getResultList();
    }

    public Long getNumberOfOrdersNoDelivered() {
        return (Long) getSession().createQuery("select count(o) from Order o where delivered = false").uniqueResult();
    }

}