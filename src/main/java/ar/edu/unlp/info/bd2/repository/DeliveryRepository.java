package ar.edu.unlp.info.bd2.repository;

import ar.edu.unlp.info.bd2.DeliveryException;
import ar.edu.unlp.info.bd2.model.Address;
import ar.edu.unlp.info.bd2.model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeliveryRepository {

    @Autowired
    SessionFactory sessionFactory;

    /**
     * Save generico para todos
     */
    public void save(Object obj) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(obj);
        session.getTransaction().commit();
    }

}
