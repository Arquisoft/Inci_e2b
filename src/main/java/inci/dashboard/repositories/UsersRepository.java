package inciDashboard.repositories;

import org.springframework.data.repository.CrudRepository;

import inciDashboard.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

}
