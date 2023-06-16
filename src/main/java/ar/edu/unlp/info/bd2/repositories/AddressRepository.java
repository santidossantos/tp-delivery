package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    Optional<Address> findById(Long id);

    Optional<Address> findByName(String name);

    Optional<Address> findByAddress(String address);

}