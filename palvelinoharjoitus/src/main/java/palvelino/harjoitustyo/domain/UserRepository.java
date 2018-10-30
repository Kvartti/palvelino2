package palvelino.harjoitustyo.domain;

import org.springframework.data.repository.CrudRepository;

import palvelino.harjoitustyo.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
