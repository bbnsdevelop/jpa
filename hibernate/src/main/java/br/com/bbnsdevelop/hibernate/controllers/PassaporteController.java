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

import br.com.bbnsdevelop.hibernate.entities.Passaporte;
import br.com.bbnsdevelop.hibernate.repositories.PassaporteRepository;

@RestController
@RequestMapping("/api/passaportes")
public class PassaporteController {
	
	
	@Autowired
	private PassaporteRepository repository;
	
	
	@GetMapping
	public List<Passaporte> getAll() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Passaporte getById(@PathVariable("id") Long id) {
		return repository.findById(id);
	}
		
	@PostMapping
	public ResponseEntity<Passaporte> save(@RequestBody Passaporte passaporte) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(passaporte));
	}
}
