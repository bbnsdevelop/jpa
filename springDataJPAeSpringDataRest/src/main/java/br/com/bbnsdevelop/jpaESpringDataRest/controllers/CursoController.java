package br.com.bbnsdevelop.jpaESpringDataRest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bbnsdevelop.jpaESpringDataRest.entities.Curso;
import br.com.bbnsdevelop.jpaESpringDataRest.repositories.CursoRepository;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

	@Autowired
	private CursoRepository repository;

	@GetMapping
	public List<Curso> getAll() {	
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");		
		return repository.findAll(sort);
	}
	
	@GetMapping("/v2")
	public Page<Curso> getAllComPaginacao(@RequestParam(value="size", required = true) Integer size, @RequestParam(value = "page", required = true) Integer page) {	
		PageRequest pageRequest = PageRequest.of(page, size);
		
		return repository.findAll(pageRequest);
	}

	@GetMapping("/{nome}/nomes")
	public List<Curso> getByNomes(@PathVariable("nome") String nome) {
		return repository.findByNome(nome);
	}


	@GetMapping("/{id}")
	public Curso getById(@PathVariable("id") Long id) {
		return repository.findById(id).orElseThrow();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		repository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping
	public ResponseEntity<Curso> save(@RequestBody Curso curso) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(curso));
	}
}
