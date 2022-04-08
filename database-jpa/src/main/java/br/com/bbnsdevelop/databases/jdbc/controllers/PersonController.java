package br.com.bbnsdevelop.databases.jdbc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bbnsdevelop.databases.jdbc.PersonJdbcDao;
import br.com.bbnsdevelop.databases.jdbc.dto.Person;

@RestController
@RequestMapping("/persons")
public class PersonController {
	
	@Autowired
	private PersonJdbcDao personJdbcDao;
	
	@GetMapping
	public ResponseEntity<List<Person>> getPersons(){
		return ResponseEntity.status(HttpStatus.OK).body(personJdbcDao.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> getPersonsById(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(personJdbcDao.findById(id));
	}
	
	@GetMapping("/name")
	public ResponseEntity<List<Person>> getPersonsByName(@RequestParam("name") String name){
		return ResponseEntity.status(HttpStatus.OK).body(personJdbcDao.findByName(name));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePersonsById(@PathVariable("id") Long id){
		
		personJdbcDao.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Pessoal excluida com sucesso");
	}

}
