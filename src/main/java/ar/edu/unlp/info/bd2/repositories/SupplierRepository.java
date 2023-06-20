package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    List<Supplier> findByNameContaining(String name);

    boolean existsByCuit(String cuit);

}
