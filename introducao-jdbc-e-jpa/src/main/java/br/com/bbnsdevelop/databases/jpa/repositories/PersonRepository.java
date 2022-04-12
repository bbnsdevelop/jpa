package br.com.bbnsdevelop.databases.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bbnsdevelop.databases.jpa.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

	List<Person> findByNameIgnoreCaseContaining(String name);

}
