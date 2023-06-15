package ar.edu.unlp.info.bd2.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenericRepository<T, Long> extends CrudRepository<T, Long> {

    Optional<T> getById(Long id);
}
