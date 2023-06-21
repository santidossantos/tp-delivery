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

    @Query(value="SELECT * FROM suppliers s inner join products p on p.supplier_id = s.id \n" +
            "inner join items i on i.product_id = p.id\n" +
            "inner join orders o on o.id = i.order_id\n" +
            "inner join qualifications q on q.order_id = o.id where q.score = 1 order by s.id asc", nativeQuery=true)
    List<Supplier> getSupplierWith1StarCalifications();


}
