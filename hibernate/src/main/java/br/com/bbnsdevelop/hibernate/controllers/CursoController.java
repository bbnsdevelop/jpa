package br.com.bbnsdevelop.hibernate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bbnsdevelop.hibernate.entities.Curso;
import br.com.bbnsdevelop.hibernate.repositories.CursoRepository;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
	
	
	@Autowired
	private CursoRepository repository;
	
	@GetMapping("/{id}")
	public Curso getById(@PathVariable("id") Long id) {
		return repository.findById(id);
	}
		
	@PostMapping
	public ResponseEntity<Curso> save(@RequestBody Curso curso) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(curso));
	}
}
