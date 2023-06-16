package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.ProductType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductTypeRepository extends CrudRepository<ProductType, Long> {

    List<ProductType> findByNameContaining(String name);

}