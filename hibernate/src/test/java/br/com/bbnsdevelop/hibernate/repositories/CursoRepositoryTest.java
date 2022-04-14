package br.com.bbnsdevelop.hibernate.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import br.com.bbnsdevelop.hibernate.HibernateApplication;
import br.com.bbnsdevelop.hibernate.entities.Curso;

@SpringBootTest(classes = HibernateApplication.class)
@TestMethodOrder(OrderAnnotation.class)
public class CursoRepositoryTest {
	
	@InjectMocks
	private CursoRepository repository;
	
	@Mock
	private EntityManager entityManager;
	
	
	
	@Test
	@Order(2)
	public void findById() {
		Curso curso = mockCurso();
		when(repository.findById(1L)).thenReturn(curso);
		assertEquals("JPA", curso.getNome());
	}
	
	
	@Test
	@Order(1)
	@DirtiesContext
	public void save() {
		Curso curso = new Curso(null, "Java");		
		repository.save(curso);
		assertEquals("Java", curso.getNome());
	}

	@Test
	@Order(3)
	@DirtiesContext
	public void update() {
		Curso curso = mockCurso();
		curso.setNome("Sprint boot");		
		repository.save(curso);
		assertEquals("Sprint boot", curso.getNome());
		assertEquals(1L, curso.getId());
	}
		

	private Curso mockCurso() {
		Curso curso = new Curso(1L, "JPA");
		return curso;
	}
}
