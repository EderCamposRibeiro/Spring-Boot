package project.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project.springboot.model.Person;

@Repository
@Transactional
public interface PersonRepository extends CrudRepository<Person, Long>{
	
	@Query("select p from Person p where p.name like %?1%")
	List<Person> findPersonByName(String name);

}
