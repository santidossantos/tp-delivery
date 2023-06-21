package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    Optional<Order> findById(Long id);

    boolean existsByNumber(int number);

    Long countByDeliveredTrueAndDateOfOrderBetween(Date startDate, Date endDate);

    Long countByDeliveredFalse();

    List<Order> findByClientUsername(String username);

    List<Order> findByDeliveredTrueAndDateOfOrderEqualsOrderByTotalPriceDesc(Date date, Pageable pageable);

}