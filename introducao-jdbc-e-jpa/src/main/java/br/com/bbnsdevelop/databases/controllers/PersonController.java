package br.com.bbnsdevelop.databases.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bbnsdevelop.databases.jdbc.PersonJdbcDao;
import br.com.bbnsdevelop.databases.jdbc.dto.Person;
import br.com.bbnsdevelop.databases.jpa.services.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {
	
	@Autowired
	private PersonJdbcDao personJdbcDao;
	
	@Autowired
	private PersonService service;
	
	@GetMapping
	public ResponseEntity<List<br.com.bbnsdevelop.databases.jpa.Person>> getPersons(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAllByEntityManager());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<br.com.bbnsdevelop.databases.jpa.Person> getPersonById(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(service.findByIdEntityManager(id));
	}
	
	@PostMapping
	public ResponseEntity<br.com.bbnsdevelop.databases.jpa.Person> savePerson(@RequestBody br.com.bbnsdevelop.databases.jpa.Person person){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(person));
	}
	@PutMapping("/{id}")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable("id") Long id){
		person.setId(id);
		return ResponseEntity.status(HttpStatus.OK).body(personJdbcDao.save(person));
	}
	
	@GetMapping("/name")
	public ResponseEntity<List<Person>> getPersonsByName(@RequestParam("name") String name){
		return ResponseEntity.status(HttpStatus.OK).body(personJdbcDao.findByLikeName(name));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePersonById(@PathVariable("id") Long id){
		
		personJdbcDao.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Pessoal excluida com sucesso");
	}

}
