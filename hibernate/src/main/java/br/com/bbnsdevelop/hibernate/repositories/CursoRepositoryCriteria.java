package br.com.bbnsdevelop.hibernate.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bbnsdevelop.hibernate.entities.Curso;

@Component
public class CursoRepositoryCriteria {

	
	@Autowired
	private EntityManager entityManager;
	
	
	
	public List<Curso> findAllCursosCriteria() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Curso> createQuery = criteriaBuilder.createQuery(Curso.class);
		Root<Curso> cursoRoot = createQuery.from(Curso.class);
		
		TypedQuery<Curso> query = entityManager.createQuery(createQuery.select(cursoRoot));
		return query.getResultList();
	}
}
