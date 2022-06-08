package br.com.bbnsdevelop.jpaESpringDataRest.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bbnsdevelop.jpaESpringDataRest.dto.FuncionarioDto;
import br.com.bbnsdevelop.jpaESpringDataRest.repositories.FuncionarioRepository;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	private FuncionarioRepository repository;
	
	
	@GetMapping
	public List<FuncionarioDto> getAll() {
		return repository.findAll().stream().map(f -> modelMapper.map(f, FuncionarioDto.class))
				.collect(Collectors.toList());
	}
	
		
}
