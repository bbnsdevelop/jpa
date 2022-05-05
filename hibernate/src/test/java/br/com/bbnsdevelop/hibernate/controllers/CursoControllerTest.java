package br.com.bbnsdevelop.hibernate.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bbnsdevelop.hibernate.HibernateApplication;
import br.com.bbnsdevelop.hibernate.entities.Curso;


@SpringBootTest(classes = HibernateApplication.class)
@AutoConfigureMockMvc
public class CursoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Mock
	private EntityManager entityManager;
	
	
	@Test
	void criarCursoTest() throws Exception {

		Curso curso = new Curso(null, "JavaScript", LocalDateTime.now(), null, null);
		mockMvc.perform(
				post("/api/cursos").contentType("application/json").content(objectMapper.writeValueAsString(curso)))
				.andExpect(status().isCreated());
	}
	
	@Test
	void buscarCursoPeloIdTest() throws Exception {
		mockMvc.perform(
				get("/api/cursos/1").contentType("application/json"))
				.andExpect(status().isOk());	
	}
}
