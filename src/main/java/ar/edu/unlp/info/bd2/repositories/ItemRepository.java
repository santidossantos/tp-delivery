package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {


}