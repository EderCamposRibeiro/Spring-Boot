package project.springboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project.springboot.model.Administrator;

@Repository
@Transactional
public interface AdministratorRepository extends CrudRepository<Administrator, Long>{
	
	@Query("select u from Administrator u where u.login = ?1")
	Administrator findUserByLogin(String login);

}
