package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.DeliveryMan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryManRepository extends CrudRepository<DeliveryMan, Long> {

    Optional<DeliveryMan> getById(Long id);

    Optional<DeliveryMan> getByScore(int score);

    Optional<DeliveryMan> getByEmail(String email);

    Optional<DeliveryMan> getByUsername(String username);
}