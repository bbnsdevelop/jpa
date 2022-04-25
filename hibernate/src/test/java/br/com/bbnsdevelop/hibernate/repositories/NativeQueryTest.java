package br.com.bbnsdevelop.hibernate.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.bbnsdevelop.hibernate.HibernateApplication;
import br.com.bbnsdevelop.hibernate.entities.Curso;

@SpringBootTest(classes = HibernateApplication.class)
@TestMethodOrder(OrderAnnotation.class)
public class NativeQueryTest {
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Test	
	public void findAllNativeQuery() {
		Query query = entityManager.createNativeQuery("Select * FROM TB_CURSO", Curso.class);
		List<Curso> resultList = query.getResultList();
		assertNotNull(resultList);
		
	}
	
	
	@Test	
	public void findByIdNativeQuery() {
		Query query = entityManager.createNativeQuery("Select * FROM TB_CURSO where id = ?", Curso.class);
		query.setParameter(1, 1);
		Curso result = (Curso) query.getSingleResult();
		assertNotNull(result);
		
	}
}
