package ar.edu.unlp.info.bd2.repository;

import ar.edu.unlp.info.bd2.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository
public class DeliveryRepository {

    @Autowired
    SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(Object obj) {
        this.getSession().save(obj);
    }

    public void update(Object obj) {
        this.getSession().update(obj);
    }

    public Product findProductById(Long id) {
        Query query = getSession().createQuery("FROM Product P WHERE P.id = :id");
        query.setParameter("id", id);
        List<Product> products = query.getResultList();
        return !products.isEmpty() ? products.get(query.getFirstResult()) : null;
    }

    public Product updateProductPrice(Product product) {
        getSession().update(product); // Hay que hacer eso o con HQL?
        return product;
    }
    
}
