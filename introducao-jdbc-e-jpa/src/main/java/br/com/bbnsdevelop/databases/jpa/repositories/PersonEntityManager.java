package br.com.bbnsdevelop.databases.jpa.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.bbnsdevelop.databases.jpa.Person;

@Repository
@Transactional
public class PersonEntityManager {
	
	
	@Autowired
	private EntityManager entityManager;
	
	
	public List<Person> findAll(){
		TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_person", Person.class);
		return namedQuery.getResultList();
	}
	
	public Person findById(Long id){
		TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_by_id_person", Person.class);
		namedQuery.setParameter("id", id);
		return namedQuery.getSingleResult();
	}

	
}
