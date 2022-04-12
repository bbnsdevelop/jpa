package br.com.bbnsdevelop.databases.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bbnsdevelop.databases.jpa.Person;
import br.com.bbnsdevelop.databases.jpa.services.PersonService;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PersonService service;

	@Test
	void criarPerssoaTest() throws Exception {

		Person person = new Person(null, "Jhon", "EUA", new Date());

		mockMvc.perform(
				post("/persons").contentType("application/json").content(objectMapper.writeValueAsString(person)))
				.andExpect(status().isCreated());

		//Person personSaved = service.save(person);
		
		//assertNotNull(personSaved.getId());
		//assertNotNull(personSaved.getBirthDate());
		//assertNotNull(personSaved.getLocation());
	}
	
	@Test
	void buscarPerssoaTest() throws Exception {
		mockMvc.perform(
				get("/persons").contentType("application/json"))
				.andExpect(status().isOk());	
	}
}
