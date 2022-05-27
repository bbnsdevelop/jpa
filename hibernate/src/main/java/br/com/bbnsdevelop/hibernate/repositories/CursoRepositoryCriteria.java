package br.com.bbnsdevelop.hibernate.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
	
	
	public List<Curso> findCursosLikeNameCriteria(String nomeCurso) {
		// 1 - Use criteria builder to create a Criteria Query returning the expected result object 
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Curso> createQuery = criteriaBuilder.createQuery(Curso.class);
		
		// 2 - Define roots for tables wich are involded in the query 
		Root<Curso> cursoRoot = createQuery.from(Curso.class);
		
		// 3 - Defnie Predicates etc using Criteria builder 
		Predicate like = criteriaBuilder.like(cursoRoot.get("nome"), "%".concat(nomeCurso).concat("%"));

		// 4 - Add Predicates etc to the Criteria query 
		createQuery.where(like);
		
		// 5 - Build the typedQuery using the entity manager and criteria query 
		TypedQuery<Curso> query = entityManager.createQuery(createQuery.select(cursoRoot));
		return query.getResultList();
	}
	
}
