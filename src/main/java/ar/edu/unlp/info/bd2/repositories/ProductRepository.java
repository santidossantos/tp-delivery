package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByNameContaining(String name);

    List<Product> findByTypesName(String typeName);

    List<Product> findByLastPriceUpdateDateLessThanEqual(Date date);

    List<Product> findAllByOrderByPriceDesc(Pageable pageable);

    /*                                                                                    *
     *      En este metodo se opta por usar la Notacion @Query ya que no es posible       *
     *      implementarlo usando nombre de metodo, dado que no se soportan                *
     *      subconsultas usando nombres de metodos                                        *
     *                                                                                    */
    @Query("SELECT p FROM Product p WHERE p.id NOT IN (SELECT i.product.id FROM Item i )")
    List<Product> findAllByIdNotIn();

    /*                                                                                    *
     *      En este metodo se opta por usar la Notacion @Query ya que no es posible       *
     *      implementarlo usando nombre de metodo, dado que la clausula Group By no       *
     *      esta Soportada por Spring Data.                                               *
     *                                                                                    */
    @Query("SELECT i.product FROM Item i GROUP BY i.product ORDER BY SUM(i.quantity) DESC")
    List<Product> findProductsOrderedByQuantity(Pageable pageable);

}