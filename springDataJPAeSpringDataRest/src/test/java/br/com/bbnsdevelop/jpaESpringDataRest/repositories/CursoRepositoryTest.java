package br.com.bbnsdevelop.jpaESpringDataRest.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import br.com.bbnsdevelop.jpaESpringDataRest.JPADataRestApplication;
import br.com.bbnsdevelop.jpaESpringDataRest.entities.Curso;

@SpringBootTest(classes = JPADataRestApplication.class)
@TestMethodOrder(OrderAnnotation.class)
public class CursoRepositoryTest {
	
	@Mock
	private CursoRepository repository;
	
	@Spy
	private CursoRepository repositorySpy;
	
	@Mock
	private EntityManager entityManager;
	
	
	
	@Test
	@Order(2)
	public void findById() {
		Curso curso = mockCurso();
		Optional<Curso> cursoOpt = Optional.of(curso);
		when(repository.findById(1L)).thenReturn(cursoOpt);
		assertEquals("JPA", curso.getNome());
		assertTrue(cursoOpt.isPresent());
	}
	
	
	@Test
	@Order(1)
	@DirtiesContext
	public void save() {
		Curso curso = new Curso(null, "Java", LocalDateTime.now(), null, null, null);		
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
	
	@Test
	@Order(4)
	public void deletar() {
		long id = 1L;
		doNothing().when(repositorySpy).deleteById(id);
		repositorySpy.deleteById(id);
		verify(repositorySpy).deleteById(id);
	}
	
	@Test
	@Order(5)
	public void deletarException() {
		long id = 1L;
		doThrow(new IllegalArgumentException("Curso não encontrado")).when(repositorySpy).deleteById(id);
		
	}

	private Curso mockCurso() {
		Curso curso = new Curso(1L, "JPA", LocalDateTime.now(), LocalDateTime.now(), null, null);
		return curso;
	}
}
