package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.Qualification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QualificationRepository extends CrudRepository<Qualification, Long> {

    Optional<Qualification> findById(Long id);
}