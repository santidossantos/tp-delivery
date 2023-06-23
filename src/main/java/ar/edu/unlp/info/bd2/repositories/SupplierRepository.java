package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    List<Supplier> findByNameContaining(String name);

    boolean existsByCuit(String cuit);

    List<Supplier> findByProductsNull();

    @Query("SELECT s FROM Supplier s JOIN Product p ON p.supplier = s.id GROUP BY s.id ORDER BY COUNT(p.id) DESC")
    List<Supplier> findSuppliersOrderedByProductCount();

    @Query("select distinct s from Supplier s join s.products p" +
            "    join Item i on i.product = p.id" +
            "    join Order o on o.id = i.order" +
            "    join Qualification q on q.order = o.id where q.score = 1 order by s.id asc")
    List<Supplier> getSupplierWith1StarCalifications();


}
