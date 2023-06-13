package ar.edu.unlp.info.bd2.repository;

import ar.edu.unlp.info.bd2.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

}