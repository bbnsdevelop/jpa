package br.com.bbnsdevelop.hibernate.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.bbnsdevelop.hibernate.HibernateApplication;
import br.com.bbnsdevelop.hibernate.entities.Curso;

@SpringBootTest(classes = HibernateApplication.class)
@TestMethodOrder(OrderAnnotation.class)
public class JPQLTest {
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Test	
	public void findAll() {
		List<Curso> resultList = entityManager.createQuery("Select c FROM Curso c", Curso.class).getResultList();
		assertNotNull(resultList);
		
	}
	
	@Test	
	public void findAllByTyped() {
		TypedQuery<Curso> query = entityManager.createQuery("Select c FROM Curso c", Curso.class);
		List<Curso> resultList = query.getResultList();
		assertNotNull(resultList);
		
	}
	
}
