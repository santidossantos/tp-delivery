package ar.edu.unlp.info.bd2.repository;

import ar.edu.unlp.info.bd2.DeliveryException;
import ar.edu.unlp.info.bd2.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.hibernate.query.Query;
import javax.persistence.TemporalType;
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

    /*                                       ETAPA II                                             */

    public List<User> getTopNUserWithMoreScore(int n) {
        return simpleQueryFactory("from User order by score desc", User.class).setMaxResults(n).getResultList();
    }

    public List<DeliveryMan> getTopNDeliveryManWithMoreOrders(int n) {
        return simpleQueryFactory("from DeliveryMan order by numberOfSuccessOrders desc", DeliveryMan.class)
                .setMaxResults(n).getResultList();
    }

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

    public Long getNumberOfOrderDeliveredAndBetweenDates(Date startDate, Date endDate) {
        return simpleQueryFactory(
                "select count(o) from Order o " +
                        "where o.delivered = true and o.dateOfOrder between :startDate and :endDate", Long.class)
                .setParameter("startDate", startDate).setParameter("endDate", endDate)
                .getSingleResult();
    }

    public Optional<Order> getOrderDeliveredMoreExpansiveInDate(Date date) {
        return simpleQueryFactory("from Order where delivered = true and date(dateOfOrder) = :date order by totalPrice desc", Order.class)
                .setParameter("date", date, TemporalType.DATE)
                .setMaxResults(1)
                .uniqueResultOptional();
    }

    public List<Supplier> getSuppliersWithoutProducts(){
        return simpleQueryFactory("from Supplier s where s.products is empty", Supplier.class).getResultList();
    }

    public List<Product> getProductsWithPriceDateOlderThan(int days) {
        LocalDate maxDate = LocalDate.now().minusDays(days);
        Date date = Date.from(maxDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return simpleQueryFactory("select p from Product p where p.lastPriceUpdateDate <= :maxDate", Product.class)
                .setParameter("maxDate", date)
                .getResultList();
    }

    public List<Product> getTop5MoreExpansiveProducts() {
        return simpleQueryFactory("select p from Product p order by p.price desc", Product.class)
                .setMaxResults(5)
                .getResultList();
    }

    public Optional<Product> getMostDemandedProduct() {
        Query<Product> query = simpleQueryFactory("select p from Item i join Product p on i.product = p.id group by i.product order by sum(i.quantity) desc", Product.class);
        query.setMaxResults(1);
        return query.uniqueResultOptional();
    }

    public List<Product> getProductsNoAddedToOrders(){
        return simpleQueryFactory("select p from Product p left outer join Item i on i.product = p.id where i.product is null", Product.class).getResultList();
    }

    public List<ProductType> getTop3ProductTypesWithLessProducts(){
        Query<ProductType> query = simpleQueryFactory("select pt from ProductType pt join pt.products p group by pt.id order by count(p.id) asc", ProductType.class);
        query.setMaxResults(3);
        return query.getResultList();
    }

    public Optional<Supplier> getSupplierWithMoreProducts(){
        Query<Supplier> query = simpleQueryFactory("select s from Supplier s join Product p on p.supplier = s.id group by s.id order by count(p.id) desc", Supplier.class);
        query.setMaxResults(1);
        return query.uniqueResultOptional();
    }

    public List<Supplier> getSupplierWith1StarCalifications(){
        Query<Supplier> query = simpleQueryFactory("select s from Supplier s join s.products p\n" +
                "    join Item i on i.product = p.id\n" +
                "    join Order o on o.id = i.order\n" +
                "    join Qualification q on q.order = o.id where q.score = 1 order by s.id asc", Supplier.class);
        return query.getResultList();
    }

}