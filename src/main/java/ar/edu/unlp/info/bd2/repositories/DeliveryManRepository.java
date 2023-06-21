package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.DeliveryMan;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryManRepository extends CrudRepository<DeliveryMan, Long> {

    Optional<DeliveryMan> findByFreeTrue();

    List<DeliveryMan> findAllByOrderByNumberOfSuccessOrdersDesc(Pageable pageable);

}