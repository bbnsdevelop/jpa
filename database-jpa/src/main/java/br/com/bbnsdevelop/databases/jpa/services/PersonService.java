package br.com.bbnsdevelop.databases.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bbnsdevelop.databases.jpa.Person;
import br.com.bbnsdevelop.databases.jpa.repositories.PersonEntityManager;
import br.com.bbnsdevelop.databases.jpa.repositories.PersonRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	
	@Autowired
	private PersonEntityManager personEntityManager;
	
	
	public List<Person> findAllByEntityManager(){
		log.info("buscando todos as pessoas com EntityManager");
		return personEntityManager.findAll();		
	}
	
	public Person findByIdEntityManager(Long id) {
		log.info("buscando pessoa EntityManager : pelo id {} ", id);		
		return personEntityManager.findById(id);
	}
	
	public List<Person> findAll(){
		log.info("buscando todos as pessoas");
		return personRepository.findAll();		
	}

	public Person findById(Long id) {
		log.info("buscando pessoa: pelo id{} ", id);		
		return personRepository.findById(id).orElse(null);
	}
	
	public Person save(Person person) {
		log.info("inserindo/atualizando uma pessoa: {} ", person);		
		return personRepository.save(person);
	}
	
	public List<Person> findByLikeName(String name) {
		log.info("buscando pessoa com o nome parecido: {} ", name);				
		return personRepository.findByNameIgnoreCaseContaining(name);
	}
	
	public void deleteById(Long id) {
			log.info("deletando pessoa pelo id: {} ", id);
		personRepository.deleteById(id);
		
	}
	

}
