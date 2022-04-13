package br.com.bbnsdevelop.hibernate.repositories;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.bbnsdevelop.hibernate.entities.Curso;

@Repository
public class CursoRepository {
	
	
	@Autowired
	private EntityManager entityManager;
	
	
	
	public Curso findById(Long id) {
		return entityManager.find(Curso.class, id);
	}

}
