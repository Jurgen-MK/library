package kz.ktzh.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kz.ktzh.models.Users;

public interface UserRepository extends CrudRepository<Users, Long> {
	List<Users> findByUsername(String username);
}
