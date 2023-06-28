package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    boolean existsByName(String name);

    List<Product> findByNameContaining(String name);

    List<Product> findByTypesName(String typeName);

    List<Product> findByLastPriceUpdateDateLessThanEqual(Date date);

    List<Product> findAllByOrderByPriceDesc(Pageable pageable);

    List<Product> findAllByIdNotIn(List<Long> ids);

    /*                                                                                    *
     *      En este metodo se opta por usar la Notacion @Query ya que no es posible       *
     *      implementarlo usando nombre de metodo, dado que la clausula Group By no       *
     *      esta Soportada por Spring Data.                                               *
     *                                                                                    */
    @Query("SELECT p FROM Item i JOIN Product p ON i.product = p.id GROUP BY i.product ORDER BY SUM(i.quantity) DESC")
    List<Product> findProductsOrderedByQuantity(Pageable pageable);

}