package br.com.bbnsdevelop.hibernate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bbnsdevelop.hibernate.dto.FuncionarioDto;
import br.com.bbnsdevelop.hibernate.repositories.FuncionarioRepository;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {
	
	
	@Autowired
	private FuncionarioRepository repository;
	
	
	@GetMapping
	public List<FuncionarioDto> getAll() {
		return repository.getAll();
	}
	
		
}
