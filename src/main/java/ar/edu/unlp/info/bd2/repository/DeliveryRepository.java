package ar.edu.unlp.info.bd2.repository;

import ar.edu.unlp.info.bd2.model.DeliveryMan;
import ar.edu.unlp.info.bd2.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    public void save(Object obj) {
        this.getSession().save(obj);
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

}
