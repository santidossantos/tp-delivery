package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.Supplier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    boolean existsByCuit(String cuit);

    List<Supplier> findByNameContaining(String name);

    List<Supplier> findByProductsNull();

    /*                                                                                    *
     *      En este metodo se opta por usar la Notacion @Query ya que no es posible       *
     *      implementarlo usando nombre de metodo, dado que la clausula Group By no       *
     *      esta Soportada por Spring Data y ademas tampoco existe una propiedad SIZE     *
     *      para nombre de metodo                                                         *
     *                                                                                    */
    @Query("SELECT s FROM Supplier s ORDER BY SIZE(s.products) DESC")
    List<Supplier> findBySuppliersOrderedByProductCount(Pageable pageable);

    /*                                                                                    *
     *      En este metodo se opta por usar la Notacion @Query ya que no es posible       *
     *      implementarlo usando nombre de metodo, dado que no es posible establecer un   *
     *      camino desde el Modelo Supplier hasta las calificaciones, partiendo desde     *
     *      SupplierRepository.                                                           *
     *                                                                                    */
    @Query("SELECT DISTINCT s from Supplier s JOIN s.products p" +
            "    JOIN Item i ON i.product = p.id" +
            "    JOIN Order o ON o.id = i.order" +
            "    JOIN Qualification q ON q.order = o.id WHERE q.score = 1 ORDER BY s.id ASC")
    List<Supplier> findBySupplierWith1StarCalifications();

}