package ar.edu.unlp.info.bd2.repository;

import ar.edu.unlp.info.bd2.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}