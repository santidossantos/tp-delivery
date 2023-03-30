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

    // Esto es lo que hay que hacer mas o menos segun Luciana
    public void save(Client c) throws DeliveryException {
        try{
            this.sessionFactory.getCurrentSession().save(c); // podemos facotorizar el metodo save... ?

        } catch (Exception e) {
            throw new DeliveryException(e.getMessage());
        }
    }

}
