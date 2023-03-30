package ar.edu.unlp.info.bd2.repository;

import ar.edu.unlp.info.bd2.DeliveryException;
import ar.edu.unlp.info.bd2.model.Client;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeliveryRepository {

    // necesitas el sessionFactory

    @Autowired
    SessionFactory sessionFactory;


    public void save(Client c) throws DeliveryException {
        try{
            this.sessionFactory.getCurrentSession().save(c);

        } catch (Exception e) {
            throw new DeliveryException(e.getMessage());
        }
    }

}
