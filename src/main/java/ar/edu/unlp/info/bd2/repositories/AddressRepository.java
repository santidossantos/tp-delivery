package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.Address;
import ar.edu.unlp.info.bd2.model.Client;
import ar.edu.unlp.info.bd2.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    Optional<Address> getById(Long id);

    Optional<Address> getByName(String name);

    Optional<Address> getByAddress(String address);

}