package br.com.bbnsdevelop.hibernate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bbnsdevelop.hibernate.entities.Estudante;
import br.com.bbnsdevelop.hibernate.repositories.EstudanteRepository;

@RestController
@RequestMapping("/api/estudantes")
public class EstudanteController {
	
	
	@Autowired
	private EstudanteRepository repository;
	
	
	@GetMapping
	public List<Estudante> getAll() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Estudante getById(@PathVariable("id") Long id) {
		return repository.findById(id);
	}
		
	@PostMapping
	public ResponseEntity<Estudante> save(@RequestBody Estudante estudante) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(estudante));
	}
}
