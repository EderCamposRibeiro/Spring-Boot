package project.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project.springboot.model.Person;

@Repository
@Transactional //We exchanged the "CrudRepository" to the "JpaRepository" to aggregate new Query options(Pagination is one of them).
public interface PersonRepository extends JpaRepository<Person, Long>{ 
	
	@Query("select p from Person p where p.name like %?1%")
	List<Person> findPersonByName(String name);
	
	@Query("select p from Person p where p.name like %?1% and p.sex like %?2% ")
	List<Person> findPersonByNameAndSex(String name, String sex);
	
	@Query("select p from Person p where p.sex like ?1 ")
	List<Person> findPersonBySex( String sex);

}
