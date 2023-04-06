package ar.edu.unlp.info.bd2.repository;

import ar.edu.unlp.info.bd2.DeliveryException;
import ar.edu.unlp.info.bd2.model.DeliveryMan;
import ar.edu.unlp.info.bd2.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;


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

}
