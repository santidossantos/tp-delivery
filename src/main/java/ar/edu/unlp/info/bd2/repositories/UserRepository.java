package ar.edu.unlp.info.bd2.repositories;

import ar.edu.unlp.info.bd2.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}