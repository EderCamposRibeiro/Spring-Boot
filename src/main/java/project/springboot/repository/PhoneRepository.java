package project.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project.springboot.model.Telephone;

@Repository
@Transactional
public interface PhoneRepository extends CrudRepository<Telephone, Long>{

}
