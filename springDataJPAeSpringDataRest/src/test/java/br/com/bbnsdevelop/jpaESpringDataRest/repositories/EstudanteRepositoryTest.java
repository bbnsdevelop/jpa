package br.com.bbnsdevelop.jpaESpringDataRest.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import br.com.bbnsdevelop.jpaESpringDataRest.entities.Estudante;

@SpringBootTest(classes = JPADataRestApplication.class)
@TestMethodOrder(OrderAnnotation.class)
public class EstudanteRepositoryTest {
	
	@Mock
	private EstudanteRepository repository;
	
	@Spy
	private EstudanteRepository repositorySpy;
	
	@Mock
	private EntityManager entityManager;
	
	
	
	@Test
	@Order(2)
	public void findById() {
		Estudante estudante = mockC();
		Optional<Estudante> estudanteOpt = Optional.of(estudante);
		when(repository.findById(1L)).thenReturn(estudanteOpt);
		assertEquals("Willian", estudante.getNome());
		assertTrue(estudanteOpt.isPresent());
	}
	
	
	@Test
	@Order(1)
	@DirtiesContext
	public void save() {
		Estudante estudante = new Estudante(1L, "Willian", null, null);	
		repository.save(estudante);
		assertEquals("Willian", estudante.getNome());
	}

	@Test
	@Order(3)
	@DirtiesContext
	public void update() {
		Estudante estudante = mockC();
		estudante.setNome("Maicon");		
		repository.save(estudante);
		assertEquals("Maicon", estudante.getNome());
		assertEquals(1L, estudante.getId());
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
		doThrow(new IllegalArgumentException("Estudante não encontrado")).when(repositorySpy).deleteById(id);
		
	}

	private Estudante mockC() {
		Estudante estudante = new Estudante(1L, "Willian", null, null);
		return estudante;
	}
}
