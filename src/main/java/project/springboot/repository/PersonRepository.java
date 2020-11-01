package project.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	default Page<Person> findPersonByNamePage(String name, Pageable pageable) {
		
		Person person = new Person();
		person.setName(name);
		
		/*We are configuring the search to find by parts of the name on the database, equal to "like" on SQL*/
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		/*The union of the things that we are searching - The value and the configuration to search*/
		Example<Person> example = Example.of(person, exampleMatcher);
		
		Page<Person> persons = findAll(example, pageable);
		
		return persons;
		
	}

	default Page<Person> findPersonBySexPage(String sex, Pageable pageable) {
		
		Person person = new Person();
		person.setSex(sex);
		
		/*We are configuring the search to find by parts of the name on the database, equal to "like" on SQL*/
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("sex", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());		
		
		/*The union of the things that we are searching - The value and the configuration to search*/
		Example<Person> example = Example.of(person, exampleMatcher);
		
		Page<Person> persons = findAll(example, pageable);
		
		return persons;
		
	}	
	
	default Page<Person> findPersonByNameAndSexPage(String name, String sex, Pageable pageable) {
		
		Person person = new Person();
		person.setName(name);
		person.setSex(sex);
		
		/*We are configuring the search to find by parts of the name on the database, equal to "like" on SQL*/
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("sex", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		/*The union of the things that we are searching - The value and the configuration to search*/
		Example<Person> example = Example.of(person, exampleMatcher);
		
		Page<Person> persons = findAll(example, pageable);
		
		return persons;
		
	}	

}
