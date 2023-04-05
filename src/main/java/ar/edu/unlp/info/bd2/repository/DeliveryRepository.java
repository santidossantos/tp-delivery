package ar.edu.unlp.info.bd2.repository;

import ar.edu.unlp.info.bd2.DeliveryException;
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
            throw new DeliveryException("mensaje del test");
        } catch (Exception e) {
            throw new DeliveryException("mensaje del test 2");
        }
    }

    public void update(Object obj) {
        this.getSession().update(obj);
    }

    public Optional<Product> findProductById(Long id) {
        return (Optional<Product>) getSession().createQuery("FROM Product P WHERE P.id = :id")
                .setParameter("id", id)
                .uniqueResultOptional();
    }

    public Product updateProductPrice(Product product) {
        getSession().update(product); // Hay que hacer eso o con HQL?
        return product;
    }
    
}
